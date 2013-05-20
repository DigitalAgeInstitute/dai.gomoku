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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * @return the challengerUsername
	 */
	public String getChallengerUsername() {
		return challengerUsername;
	}

	/**
	 * @return the challengeeUsername
	 */
	public String getChallengeeUsername() {
		return challengeeUsername;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param gameID
	 *            the gameID to set
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	/**
	 * @param challengerUsername
	 *            the challengerUsername to set
	 */
	public void setChallengerUsername(String challengerUsername) {
		this.challengerUsername = challengerUsername;
	}

	/**
	 * @param challengeeUsername
	 *            the challengeeUsername to set
	 */
	public void setChallengeeUsername(String challengeeUsername) {
		this.challengeeUsername = challengeeUsername;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void process() {
		// TODO: If it is a CHALLENGE, accept or reject
		if (message.equals("MAKE")) {
			int response = controller
					.displayChallengeDialog(challengerUsername);
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
			// TODO: If it is an accept, see if the game id exists and use it
			// TODO: If game id doesn't exist, probably ignore...
			controller.startNewGame(gameID, challengerUsername,
					challengeeUsername);
		} else {
			// TODO: Display challenge rejected
			controller.displayRejectDialog(challengeeUsername);
		}
	}

}
