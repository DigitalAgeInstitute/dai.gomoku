package dai.gomoku.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GomokuDaemon implements Runnable {
	private boolean stopped = false;

	private ServerSocket ssocket;
	private ExecutorService serviceThreads;

	// TODO: Add other relevant variables as they are needed

	public GomokuDaemon(int port) throws IOException {
		initSSocket(port);
		serviceThreads = Executors.newCachedThreadPool();
	}

	public void stop() {
		this.stopped = true;
	}

	private void initSSocket(int port) throws IOException {
		this.ssocket = new ServerSocket(port);
	}

	private void releaseResources() {
		try {
			closeSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeSocket() throws IOException {
		if (ssocket != null) {
			ssocket.close();
		}
	}

	@Override
	public void run() {
		System.out
				.println("Server listening on port " + ssocket.getLocalPort());
		while (!stopped) {
			Socket clientSocket;
			try {
				clientSocket = ssocket.accept();
				String acceptMessage = String.format(
						"Serving client of address %s on port %d", clientSocket
								.getInetAddress().getHostAddress(),
						clientSocket.getLocalPort());
				Logger.getLogger(GomokuDaemon.class.getName()).log(Level.INFO,
						acceptMessage);
				RequestHandler handler = new RequestHandler(clientSocket);
				serviceThreads.execute(handler);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		serviceThreads.shutdown();
		releaseResources();
	}

	public static void main(String[] args) {
		ExecutorService daemonThread = Executors.newCachedThreadPool();
		try {
			GomokuDaemon daemon = new GomokuDaemon(4010);
			daemonThread.execute(daemon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		daemonThread.shutdown();
	}

}
