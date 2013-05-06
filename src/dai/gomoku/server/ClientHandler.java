package dai.gomoku.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private boolean stopped;

	private InputStream inputFromClient;
	private OutputStream outputToClient;

	private ExecutorService handlers;

	private final static String TERMINATION_MESSAGE = "\"ServerMessage\":{\"Server has terminated. Please try reconnecting after some time.\"}";

	public ClientHandler(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		stopped = false;
		initIO();
		handlers = Executors.newCachedThreadPool();
	}

	public boolean isStopped() {
		return stopped;
	}

	public void stop() {
		stopped = true;
	}

	@Override
	public void run() {
		while (!stopped) {
			// TODO: Implement what the system does
			try {
				int num = inputFromClient.available();
				
				if (num != 0) {
					System.err.println(num);
					JSONRequestHandler jsonParserHandler = new JSONRequestHandler(
							inputFromClient, outputToClient);
					handlers.execute(jsonParserHandler);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * try { Thread.sleep(500000); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
		}

		informClientOfServerTermination();
		releaseResources();
	}

	private void initIO() throws IOException {
		inputFromClient = clientSocket.getInputStream();
		outputToClient = clientSocket.getOutputStream();
	}

	private void informClientOfServerTermination() {
		PrintWriter writer = new PrintWriter(outputToClient);
		writer.write(TERMINATION_MESSAGE);
		writer.close();
	}

	private void releaseResources() {
		try {
			closeIO();
			closeSocket();
		} catch (IOException ioe) {
			Logger.getLogger(ClientHandler.class.getName()).log(
					Level.SEVERE,
					"There was an error releasing resources:\n"
							+ ioe.getStackTrace().toString());
		}
	}

	private void closeIO() throws IOException {
		if (inputFromClient != null) {
			inputFromClient.close();
		}
		if (outputToClient != null) {
			outputToClient.close();
		}
	}

	private void closeSocket() throws IOException {
		if (this.clientSocket != null) {
			clientSocket.close();
		}
	}

}
