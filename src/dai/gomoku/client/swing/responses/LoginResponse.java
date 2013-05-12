package dai.gomoku.client.swing.responses;

import dai.gomoku.client.Response;

public class LoginResponse implements Response {
	private String type = "LOGIN";
	private String state;

	public LoginResponse(String state) {
		this.state = state;
	}

	@Override
	public void process() {
		if (state.equals("OK")) {
			// TODO: Allow system to continue
		} else {
			// TODO: Display error message.
			// TODO: Redisplay login screen
		}
	}

}
