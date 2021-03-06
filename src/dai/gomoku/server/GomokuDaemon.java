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

	@Override
	public void run() {
		System.out
				.println("Server listening on port " + ssocket.getLocalPort());
		HeartBeatChecker heartBeat = HeartBeatChecker.getInstance();
		serviceThreads.execute(heartBeat);
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
				JSONRequestHandler clientHandler = new JSONRequestHandler(
						clientSocket);
				serviceThreads.execute(clientHandler);
			} catch (IOException e) {
				Logger.getLogger(GomokuDaemon.class.getName()).log(
						Level.SEVERE, e.getLocalizedMessage());
			}
		}

		serviceThreads.shutdown();
		releaseResources();
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

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("----------------------------------");
			System.out.println("              USAGE               ");
			System.out.println("java GomokuDaemon <port>");
			System.out.println("----------------------------------");
			System.out.println("Quiting daemon...");
		} else {
			ExecutorService daemonThread = Executors.newCachedThreadPool();
			try {
				int port = Integer.parseInt(args[0]);
				GomokuDaemon daemon = new GomokuDaemon(port);
				daemonThread.execute(daemon);
			} catch (IOException e) {
				Logger.getLogger(GomokuDaemon.class.getName()).log(
						Level.SEVERE, e.getLocalizedMessage());
				e.printStackTrace();
			} catch (NumberFormatException nfe) {
				System.err.println(String
						.format("Invalid port number '%s'. I only "
								+ "take integer values.\nQuiting daemon....", args[0]));
			}
			daemonThread.shutdown();
		}
	}

}
