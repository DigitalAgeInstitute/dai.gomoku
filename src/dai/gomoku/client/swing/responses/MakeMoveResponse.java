package dai.gomoku.client.swing.responses;

import javax.swing.JOptionPane;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class MakeMoveResponse implements Response {
	public static final String OK = "OK";
	public static final String FAIL = "FAIL";

	private String type = "MAKEMOVE";
	private String status;
	private String gameID;
	private String username;
	private int row;
	private int col;
	
	ClientController controller;
	

	public MakeMoveResponse(String status, String gameID, String username,
			int row, int col, ClientController controller) {
		this.status = status;
		this.gameID = gameID;
		this.username = username;
		this.row = row;
		this.col = col;
		this.controller = controller;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public void process() {
		if (status.equals(OK)) {
			controller.markCell(gameID, row, col, username);
		} else {
			JOptionPane.showMessageDialog(null, "Sorry. That move is invalid.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MakeMoveResponse [type=" + type + ", status=" + status
				+ ", gameID=" + gameID + ", username=" + username + ", row="
				+ row + ", col=" + col + "]";
	}

}