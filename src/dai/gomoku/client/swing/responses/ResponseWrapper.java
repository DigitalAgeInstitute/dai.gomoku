package dai.gomoku.client.swing.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import dai.gomoku.game.core.HumanPlayer;

public class ResponseWrapper {
	@SerializedName("type")
	private String type;

	@SerializedName("username")
	private String username;

	@SerializedName("state")
	private String state;

	@SerializedName("status")
	private String status;

	@SerializedName("gameID")
	private String gameID;

	@SerializedName("row")
	private int row;

	@SerializedName("col")
	private int col;

	@SerializedName("players")
	private List<HumanPlayer> players;

	@SerializedName("challengerUsername")
	private String challengerUsername;

	@SerializedName("challengeeUsername")
	private String challengeeUsername;

	@SerializedName("message")
	private String message;

	@SerializedName("winner")
	private String winner;

	/*
	 * Getter methods
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the status
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return the players
	 */
	public List<HumanPlayer> getPlayers() {
		return players;
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
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseWrapper [type=" + type + ", username=" + username
				+ ", state=" + state + ", status=" + status + ", gameID="
				+ gameID + ", row=" + row + ", col=" + col + ", players="
				+ players + ", challengerUsername=" + challengerUsername
				+ ", challengeeUsername=" + challengeeUsername + ", message="
				+ message + ", winner=" + winner + "]";
	}

}
