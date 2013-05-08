package dai.gomoku.server.requests;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;

public class MakeMoveRequest implements Request {
	private String type = "MAKEMOVE";
	private String gameID;
	private String username;
	private int row;
	private int col;

	public MakeMoveRequest(String gameID, String username, int row, int col) {
		this.gameID = gameID;
		this.username = username;
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * @param gameID
	 *            the gameID to set
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	@Override
	public Response process() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MakeMoveRequest [type=" + type + ", gameID=" + gameID
				+ ", playerID=" + username + ", row=" + row + ", col=" + col
				+ "]";
	}

}
