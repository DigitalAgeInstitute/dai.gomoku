package dai.gomoku.client.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ClientThread extends Thread {

	static final String SERVER_IP = "192.168.3.122";
	static final int SERVER_PORT = 4010;

	Handler handler ;
	String strReceived;
	static Socket socket;

	PrintWriter out;
	BufferedReader br;
	ResponseFactory responseFactory;
	public ClientThread(Handler handler) {
		this.handler=handler;
	}
	public void run() {
		Looper.prepare();
		try {
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

			

			socket = new Socket(serverAddr, SERVER_PORT);
			try {
				// ---get an OutputStream object to send to the server---
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);

				// ---get an InputStream object to read from the server---
				br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				try {
					// ---read all incoming data terminated with a \n
					// char---
					
					String line = "";
					while ((line = br.readLine()) != null) {
						if (line.equals("[STARTJSON]")) {
							strReceived = "";
							while ((line = br.readLine()) != null) {
								if (line.equals("[ENDJSON]")) {
									break;
								}
								strReceived += line;
								handler.sendEmptyMessage(0);
								try {
									responseFactory = new ResponseFactory(
											strReceived);
									Log.e("recieve from server", strReceived);
									responseFactory.doCalledResponse();
								} catch (Exception e) {
									e.printStackTrace();
									Log.e("doesnt get from server", strReceived);
								}
							}
						}

						// ---disconnected from the server---
					
					}
				} catch (Exception e) {
					final String error = e.getLocalizedMessage();
					
				}

			} catch (Exception e) {
				final String error = e.getLocalizedMessage();
				
			}

			handler.post(new Runnable() {
				@Override
				public void run() {
					// connection closed
				}
			});
			Looper.loop();
		} catch (Exception e) {
			final String error = e.getLocalizedMessage();
			handler.post(new Runnable() {
				@Override
				public void run() {
					// textView1.setText(textView1.getText() + "\n" + error);
				}
			});
		}
	}

	public void sendMessage(String message) {
		if (out != null && !out.checkError()) {
			String msg = "\n[STARTJSON]\n" + message + "\n[ENDJSON]\n";
			Log.e("json", msg);
			// out.println(msg);
			out.write(msg);
			out.flush();
		}
	}

	/*
	 * protected void onStart() { Thread clientThread = new Thread(new
	 * ClientThread());
	 * 
	 * }
	 */

	protected void onStop() {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}