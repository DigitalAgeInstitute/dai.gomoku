package dai.gomoku.client.swing.responses;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class RegisterResponse implements Response {
	private String status;
	private String message;

	private ClientController controller;

	public RegisterResponse(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public void process() {
		if (status.equals("OK")) {
			controller.displayRegisterSuccessDialog();
			controller.displayLoginScreen();
		} else {
			controller.displayRegisterFailDialog(message);
			controller.displayRegisterScreen();
		}

	}

}
