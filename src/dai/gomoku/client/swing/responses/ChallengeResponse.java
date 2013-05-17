package dai.gomoku.client.swing.responses;

import javax.swing.JOptionPane;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class ChallengeResponse implements Response {
	private String type = "CHALLENGE";
	private String gameID;
	private String challengerUsername;
	private String challengeeUsername;
	private String message;

	private ClientController controller;

	public ChallengeResponse(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public void process() {
		// TODO: If it is a CHALLENGE, accept or reject
		if (message.equals("MAKE")) {
			int response = controller
					.displayChallengeDialog(challengeeUsername);
			if (response == JOptionPane.YES_OPTION) {
				String jsonString = String
						.format("{ \"type\":\"%s\", \"gameID\":\"%s\", \"challengerUsername\":\"%s\", \"challengeeUsername\":\"%s\", \"message\":\"%s\" }",
								type, gameID, challengerUsername,
								challengeeUsername, "ACCEPT");
				controller.sendToServer(jsonString);
			} else {
				String jsonString = String
						.format("{ \"type\":\"%s\", \"gameID\":\"%s\", \"challengerUsername\":\"%s\", \"challengeeUsername\":\"%s\", \"message\":\"%s\" }",
								type, gameID, challengerUsername,
								challengeeUsername, "REJECT");
				controller.sendToServer(jsonString);
			}
		} else if (message.equals("ACCEPT")) {
			// TODO: Create and display game window
			controller.startNewGame(gameID, challengerUsername, challengeeUsername);
		} else {
			// TODO: Display challenge rejected
			controller.displayRejectDialog(challengeeUsername);
		}
		// TODO: If it is an accept, see if the game id exists and use it
		// TODO: If game id doesn't exist, probably ignore...
	}

}
