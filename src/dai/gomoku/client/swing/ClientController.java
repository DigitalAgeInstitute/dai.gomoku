package dai.gomoku.client.swing;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import dai.gomoku.client.swing.requests.ChallengeRequest;
import dai.gomoku.client.swing.requests.LoginRequest;
import dai.gomoku.client.swing.requests.RegisterRequest;
import dai.gomoku.game.core.HumanPlayer;

public class ClientController {
	private Socket socket;

	private String username;
	
	private GomokuLogin loginScreen;
	private RegisterWindow registerScreen;
	private GomokuGUI mainWindow;
	private List<GameModel> games;

	private PrintWriter writer;

	public ClientController(Socket socket) {
		this.socket = socket;
		initLoginScreen();
		displayLoginScreen();
		initRegisterScreen();
		initGameWindow();
		initWriter();
	}

	public Socket getSocket() {
		return socket;
	}
	
	public String getUsername ( ) {
		return username;
	}

	public void displayGameWindow() {
		mainWindow.setVisible(true);
	}

	public void hideGameWindow() {
		mainWindow.setVisible(true);
	}

	public void destroyGameWindow() {
		mainWindow.dispose();
	}

	public void populateList(List<HumanPlayer> players) {
		mainWindow.populateList(players);
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

	public void displayRegisterScreen() {
		registerScreen.setVisible(true);
	}

	public void hideRegisterScreen() {
		registerScreen.setVisible(false);
	}

	public void destroyRegisterScreen() {
		registerScreen.dispose();
	}

	public int displayChallengeDialog(String challengeeUsername) {
		return JOptionPane.showConfirmDialog(mainWindow, challengeeUsername
				+ " challenged you to a game. Accept?", "Take the Challenge",
				JOptionPane.YES_NO_OPTION);
	}

	public void displayRejectDialog(String challengeeUsername) {
		JOptionPane.showConfirmDialog(mainWindow, challengeeUsername
				+ " rejected your challenge. Another time maybe.",
				"Challenge Rejected", JOptionPane.OK_OPTION);
	}

	public void displayRegisterSuccessDialog() {
		displayLoginScreen();
		JOptionPane
				.showMessageDialog(
						loginScreen,
						"You have been registered successfully.\nPlease login with your chosen username and password.",
						"Registration Failed", JOptionPane.INFORMATION_MESSAGE);
	}

	public void displayRegisterFailDialog(String message) {
		displayRegisterScreen();
		JOptionPane.showMessageDialog(registerScreen, "Registration Failed:\n"
				+ message, "Registration Failure", JOptionPane.ERROR_MESSAGE);
	}

	public void registerUser(RegisterRequest regReq ) {
		regReq.request();
	}
	
	public void signIn ( LoginRequest req ) {
		this.username = req.getUsername();
		req.request();
	}
	
	public void challengeUser ( ChallengeRequest req ) {
		req.request();
	}

	public void markCell(String gameID, int row, int col, String username) {
		// TODO: Implement this correctly
	}

	public void startNewGame(String gameID, String challenger, String challengee) {
		GameModel game = new GameModel();
		GameWindow boardUI = mainWindow.createGameWindow(gameID, challengee,
				game);
		game.addGameBoardChangeListener(boardUI);
	}

	public synchronized void sendToServer(String toSend) {
		toSend = "\n[STARTJSON]\n" + toSend + "\n[ENDJSON]\n";
		writer.write(toSend);
		writer.flush();
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
		loginScreen = new GomokuLogin(this);
	}

	private void initRegisterScreen() {
		registerScreen = new RegisterWindow(this);
	}

	private void initGameWindow() {
		mainWindow = new GomokuGUI(this, games);
	}

	private void initWriter() {
		try {
			writer = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Socket sock;
		ClientController controller;
		try {
			sock = new Socket("localhost", 4010);
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
