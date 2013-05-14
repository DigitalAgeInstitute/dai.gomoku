package dai.gomoku.client.swing.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

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

	@Override
	public String toString() {
		return "ResponseWrapper [type=" + type + ", username=" + username
				+ ", state=" + state + ", gameID=" + gameID + ", row=" + row
				+ ", col=" + col + "]";
	}

}
