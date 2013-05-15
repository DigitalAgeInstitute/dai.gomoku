package dai.gomoku.client.swing.requests;

import dai.gomoku.client.swing.ClientController;

public class LoginRequest implements Request {
	private ClientController controller;

	private String type = "LOGIN";
	private String username;
	private String password;

	public LoginRequest(ClientController controller, String username,
			String password) {
		this.controller = controller;
		this.username = username;
		this.password = password;
	}

	@Override
	public void request() {
		String jsonString = String
				.format("{ \"type\":\"%s\", \"username\":\"%s\", \"password\":\"%s\" }",
						type, username, password);
		controller.sendToServer("\n[STARTJSON]\n" + jsonString
				+ "\n[ENDJSON]\n");
	}

}
