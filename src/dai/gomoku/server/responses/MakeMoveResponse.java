package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.game.core.GomokuGame;
import dai.gomoku.game.core.Player;
import dai.gomoku.server.Response;

public class MakeMoveResponse implements Response {
	public static final String OK = "OK";
	public static final String FAIL = "FAIL";

	private String type = "MAKEMOVE";
	private String status;
	private String gameID;
	private String username;
	private int row;
	private int col;

	public MakeMoveResponse(String status, String gameID, String username,
			int row, int col) {
		this.status = status;
		this.gameID = gameID;
		this.username = username;
		this.row = row;
		this.col = col;
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
	public String toJSONString() {
		return String
				.format("{ \"type\":\"%s\", \"status\":\"%s\", \"gameID\":\"%s\", \"username\":\"%s\", \"row\":%d, \"col\":%d }",
						type, status, gameID, username, row, col);
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

	@Override
	public synchronized void respond(Socket socket) {
		try {
			// TODO: Refactor this and clean it up...
			GomokuGame game = ResponseUtil.getGameByID(
					ResponseUtil.getPlayer(username), gameID);
			Player player1 = game.getPlayer1();
			Player player2 = game.getPlayer2();

			PrintWriter writer1 = new PrintWriter(player1.getPlayerThread()
					.getClientSocket().getOutputStream());
			PrintWriter writer2 = new PrintWriter(player2.getPlayerThread()
					.getClientSocket().getOutputStream());
			writer1.write("\n[STARTJSON]\n" + toJSONString() + "\n[ENDJSON]\n");
			writer2.write("\n[STARTJSON]\n" + toJSONString() + "\n[ENDJSON]\n");
			writer1.flush();
			writer2.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
