package dai.gomoku.client.swing;

import com.google.gson.annotations.SerializedName;

public class ResponseParser {
	@SerializedName("type")
	private String type;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	@SerializedName("gameID")
	private String gameID;

	@SerializedName("row")
	private int row;

	@SerializedName("col")
	private int col;

	/*
	 * The accessor methods
	 */

	public String getType() {
		return type;
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

}

