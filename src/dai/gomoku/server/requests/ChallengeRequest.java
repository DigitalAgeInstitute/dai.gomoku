package dai.gomoku.server.requests;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.responses.ChallengeResponse;

public class ChallengeRequest implements Request {
	public static final String MAKE = "MAKE";
	public static final String ACCEPT = "ACCEPT";
	public static final String REJECT = "REJECT";
	public static final String FAIL = "FAIL";

	private String type = "CHALLENGE";
	private String challengerUsername;
	private String challengeeUsername;
	private String message;

	public ChallengeRequest(String challengerUsername,
			String challengeeUsername, String message) {
		this.challengerUsername = challengerUsername;
		this.challengeeUsername = challengeeUsername;
		this.message = message;
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
	public Response process() {
		// TODO: Send the challenge to the opponent and then set a status
		// awaiting a response.

		// TODO: Read the response from the opponent
		// TODO: If accepted, send accept, otherwise send refuse
		return new ChallengeResponse(challengerUsername, challengeeUsername,
				message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChallengeRequest [type=" + type + ", challengerUsername="
				+ challengerUsername + ", challengeeUsername="
				+ challengeeUsername + ", message=" + message + "]";
	}

}
