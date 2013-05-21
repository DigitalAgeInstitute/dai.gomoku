package dai.gomoku.client.swing;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
		this.games = new ArrayList<GameModel>();
		initLoginScreen();
		displayLoginScreen();
		initRegisterScreen();
		initGameWindow();
		initWriter();
	}

	public Socket getSocket() {
		return socket;
	}

	public String getUsername() {
		return username;
	}

	public void displayGameWindow() {
		mainWindow.setTitle("GOMOKU: "+username);
		mainWindow.setVisible(true);
	}

	public void hideGameWindow() {
		mainWindow.setVisible(true);
	}

	public void destroyGameWindow() {
		mainWindow.dispose();
	}

	public void populateList(List<HumanPlayer> players) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUserName().equals(username)) {
				players.remove(i);
				break;
			}
		}
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
				"Challenge Rejected", JOptionPane.INFORMATION_MESSAGE);
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

	public void displayMoveFailedDialog() {
		JOptionPane
				.showMessageDialog(
						mainWindow,
						"Sorry. That move failed.\nIt is probably not your turn, or you selected a cell that is already owned",
						"Invalid Move", JOptionPane.ERROR_MESSAGE);
	}

	public void displayGameOverDialog(String winner) {
		JOptionPane.showMessageDialog(mainWindow, "Game Over!\n" + winner
				+ " won.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
	}

	public void registerUser(RegisterRequest regReq) {
		regReq.request();
	}

	public void signIn(LoginRequest req) {
		this.username = req.getUsername();
		req.request();
	}

	public void challengeUser(ChallengeRequest req) {
		req.request();
	}

	public void markCell(String gameID, int row, int col, String username) {
		getGameByID(gameID).markCell(row, col, username);
	}

	public void startNewGame(String gameID, String challenger, String challengee) {
		GameModel game = new GameModel(gameID, challenger, challengee);
		GameWindow boardUI = mainWindow.createGameWindow(gameID,
				(username.equals(challenger) ? challengee : challenger), game);
		game.addGameBoardChangeListener(boardUI);
		games.add(game);
	}

	public GameModel getGameByID(String gameID) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getGameID().equals(gameID)) {
				return games.get(i);
			}
		}
		return null;
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
		mainWindow = new GomokuGUI(this);
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
