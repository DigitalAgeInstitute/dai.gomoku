package dai.gomoku.client.swing;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.client.swing.requests.LoginRequest;
import dai.gomoku.client.swing.requests.RegisterRequest;

public class ClientController implements LoginListener, RegisterListener {
	private Socket socket;

	private String username;
	private String password;

	private GomokuLogin loginScreen;
	private RegisterWindow registerScreen;
	private GomokuGUI gameWindow;
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

	public void displayRegisterScreen() {
		registerScreen.setVisible(true);
	}

	public void hideRegisterScreen() {
		registerScreen.setVisible(true);
	}

	public void destroyRegisterScreen() {
		registerScreen.dispose();
	}

	public void markCell(int row, int col, String username) {
		// TODO: Implement this correctly
	}

	@Override
	public void getLoginDetails() {
		getDetails();
		if (inputComplete()) {
			new LoginRequest(this, username, password).request();
			password = null;
		} else {
			JOptionPane.showMessageDialog(loginScreen,
					"Please fill in all the details", "Incomplete Input",
					JOptionPane.ERROR_MESSAGE);
			displayLoginScreen();
		}
	}

	@Override
	public void register() {
		hideRegisterScreen();
		RegisterRequest registerRequest = new RegisterRequest(this);
		if (checkRegisterInput(registerRequest)) {
			if (password.equals(registerScreen.getConfirmPassword())) {
				registerRequest.request();
			} else {
				JOptionPane.showMessageDialog(registerScreen,
						"The passwords you typed do not match",
						"Password Mismatch", JOptionPane.ERROR_MESSAGE);
				displayRegisterScreen();
			}

		} else {
			JOptionPane.showConfirmDialog(gameWindow,
					"Please fill in all the details", "Incomplete Input",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public synchronized void sendToServer(String toSend) {
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
		loginScreen.registerGUIListener(this);
	}

	private void initRegisterScreen() {
		registerScreen = new RegisterWindow();
	}

	private void initGameWindow() {
		gameWindow = new GomokuGUI(games);
	}

	private void getDetails() {
		this.username = loginScreen.getUsername();
		this.password = loginScreen.getPassword();
	}

	private boolean inputComplete() {
		if ((username != null) && (!username.equals("")) && (password != null)
				&& (!password.equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	private void initWriter() {
		try {
			writer = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkRegisterInput(RegisterRequest rr) {
		getRegistrationDetails(rr);
		if ((!rr.getFirstName().equals("")) && (!rr.getLastName().equals(""))
				&& (!rr.getEmail().equals("")) && (!rr.getPhone().equals(""))
				&& (!rr.getPassword().equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	private void getRegistrationDetails(RegisterRequest rr) {
		rr.setEmail(registerScreen.getEmail());
		rr.setFirstName(registerScreen.getFirstName());
		rr.setLastName(registerScreen.getLastName());
		rr.setPassword(registerScreen.getPassword());
		rr.setPhone(registerScreen.getPhone());
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
