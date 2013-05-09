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
import android.util.Log;

public class ClientThread extends Thread {

	static final String SERVER_IP = "192.168.3.106";
	static final int SERVER_PORT = 4010;

	Handler handler = new Handler();
	static Socket socket;

	PrintWriter out;
	BufferedReader br;

	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

			handler.post(new Runnable() {
				@Override
				public void run() {
					// toast connecting to server
				}
			});

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
					String line = null;
					while ((line = br.readLine()) != null) {
						final String strReceived = line;

						handler.post(new Runnable() {
							@Override
							public void run() {
								Log.e("recieve from server", strReceived);
							}
						});
					}

					// ---disconnected from the server---
					handler.post(new Runnable() {
						@Override
						public void run() {
							// client disconnected
							Log.e("client disconnected", "");
						}
					});

				} catch (Exception e) {
					final String error = e.getLocalizedMessage();
					handler.post(new Runnable() {
						@Override
						public void run() {

						}
					});
				}

			} catch (Exception e) {
				final String error = e.getLocalizedMessage();
				handler.post(new Runnable() {
					@Override
					public void run() {

					}
				});
			}

			handler.post(new Runnable() {
				@Override
				public void run() {
					// connection closed
				}
			});

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
	
	 public void sendMessage(String message){
	        if (out != null && !out.checkError()) {
	        	String msg = "\n[STARTJSON]\n" + message + "\n[ENDJSON]\n";
	        	Log.e("json", msg);
	            //out.println(msg);
	        	out.write(msg);
	            out.flush();
	        }
	    }

	/*protected void onStart() {
		Thread clientThread = new Thread(new ClientThread());
		
	}*/

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