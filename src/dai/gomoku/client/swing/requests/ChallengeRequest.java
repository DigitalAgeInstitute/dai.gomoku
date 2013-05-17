package dai.gomoku.client.swing.requests;

import dai.gomoku.client.swing.ClientController;

public class ChallengeRequest implements Request {
	private String type = "CHALLENGE";
	private String challengerUsername;
	private String challengeeUsername;
	private String message;

	private ClientController controller;

	public ChallengeRequest(ClientController controller) {
		this.controller = controller;
	}

	/**
	 * @return the challengerUsername
	 */
	public String getChallengerUsername() {
		return challengerUsername;
	}

	/**
	 * @param challengerUsername
	 *            the challengerUsername to set
	 */
	public void setChallengerUsername(String challengerUsername) {
		this.challengerUsername = challengerUsername;
	}

	/**
	 * @return the challengeeUsername
	 */
	public String getChallengeeUsername() {
		return challengeeUsername;
	}

	/**
	 * @param challengeeUsername
	 *            the challengeeUsername to set
	 */
	public void setChallengeeUsername(String challengeeUsername) {
		this.challengeeUsername = challengeeUsername;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	@Override
	public void request() {
		String challengeJSON = String
				.format("{ \"type\":\"%s\", \"challengerUsername\":\"%s\", "
						+ "\"challengeeUsername\":\"%s\", \"message\":\"%s\" }",
						type, challengerUsername, challengeeUsername, message);
		controller.sendToServer(challengeJSON);
	}

}
