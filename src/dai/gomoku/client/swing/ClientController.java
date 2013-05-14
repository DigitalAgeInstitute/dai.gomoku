package dai.gomoku.client.swing;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dai.gomoku.game.core.GomokuGame;
import dai.gomoku.game.core.HumanPlayer;

public class ClientController {
	private Socket socket;

	private GomokuLogin loginScreen;
	private GomokuGUI gameWindow;
	private List<GomokuGame> games;


	public ClientController(Socket socket) {
		this.socket = socket;
		initLoginScreen();
		displayLoginScreen();
		initGameWindow();
	}

	public Socket getSocket() {
		return socket;
	}

	public void displayGameWindow() {
		gameWindow.setVisible(true);
	}

	public void hideGameWindow() {
		gameWindow.setVisible(true);
	}

	public void destroyGameWindow() {
		gameWindow.dispose();
	}

	public void populateList(List<HumanPlayer> players) {
		gameWindow.populateList(players);
	}

	public void displayLoginScreen() {
		loginScreen.setVisible(true);
	}

	public void hideLoginScreen() {
		loginScreen.setVisible(false);
	}

	public void destroyLoginScreen() {
		loginScreen.dispose();
	}

	public void markCell ( int row, int col, String username ) {
		// TODO: Implement this correctly
	}

	public void closeConnection() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initLoginScreen() {
		this.loginScreen = new GomokuLogin(socket);
	}

	private void initGameWindow() {
		gameWindow = new GomokuGUI(socket, this);
	}

	public static void main(String[] args) {
		Socket sock;
		ClientController controller;
		try {
			sock = new Socket("10.42.0.73", 4010);
			controller = new ClientController(sock);
			ExecutorService service = Executors.newCachedThreadPool();
			service.execute(new ResponseHandler(sock, controller));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
