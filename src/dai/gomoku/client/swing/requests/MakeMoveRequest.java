package dai.gomoku.client.swing.requests;

import dai.gomoku.client.swing.ClientController;

public class MakeMoveRequest implements Request {
	private ClientController controller;

	private String type = "MAKEMOVE";
	private String gameID;
	private String username;
	private int row;
	private int col;

	public MakeMoveRequest(ClientController controller) {
		this.controller = controller;
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
	public void request() {
		String mmJson = String.format("{ \"type\":\"%s\", \"gameID\":\"%s\", "
				+ "\"username\":\"%s\", \"row\":%d, \"col\":%d }", type,
				gameID, username, row, col);
		System.out.println("\tSENDING TO SERVER: " + mmJson);
		controller.sendToServer(mmJson);
	}

}
