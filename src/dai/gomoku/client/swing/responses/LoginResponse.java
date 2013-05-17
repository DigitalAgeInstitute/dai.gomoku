package dai.gomoku.client.swing.responses;

import javax.swing.JOptionPane;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class LoginResponse implements Response {
	private String type = "LOGIN";
	private String state;
	ClientController controller;

	public String getType() {
		return type;
	}

	public LoginResponse(String state, ClientController controller) {
		this.state = state;
		this.controller = controller;
	}

	@Override
	public void process() {
		if (state.equals("OK")) {
			controller.displayGameWindow();
			controller.destroyLoginScreen();
		} else {
			JOptionPane.showMessageDialog(null,
					"We could not log you in to the system. Please check your username "
							+ "and password and try again.", "Login failed",
					JOptionPane.ERROR_MESSAGE);
			controller.displayLoginScreen();
		}
	}

}