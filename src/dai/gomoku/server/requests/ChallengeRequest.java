package dai.gomoku.server.requests;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;

public class ChallengeRequest implements Request {
	private String type = "CHALLENGE";
	private String challengerUsername;
	private String opponentUsername;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * @return the opponentUsername
	 */
	public String getOpponentUsername() {
		return opponentUsername;
	}

	/**
	 * @param opponentUsername
	 *            the opponentUsername to set
	 */
	public void setOpponentUsername(String opponentUsername) {
		this.opponentUsername = opponentUsername;
	}

	@Override
	public Response process() {
		// TODO: Send the challenge to the opponent and then set a status awaiting a response.
		// TODO: Read the response from the opponent
		// TODO: If accepted, send accept, otherwise send refuse
		return null;
	}

}
