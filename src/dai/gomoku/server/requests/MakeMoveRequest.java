package dai.gomoku.server.requests;

import dai.gomoku.game.core.CellOwnershipException;
import dai.gomoku.game.core.GomokuGame;
import dai.gomoku.game.core.Player;
import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.responses.GameOverResponse;
import dai.gomoku.server.responses.MakeMoveResponse;
import dai.gomoku.server.responses.ResponseUtil;

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
		Player player = ResponseUtil.getPlayer(username);
		GomokuGame game = ResponseUtil.getGameByID(player, gameID);
		try {
			if (game.isGameOver()) {
				return new GameOverResponse(game.getGameID(), game.getWinner()
						.getUserName());
			} else {
				game.markCell(player, row, col);
				if ( game.isGameOver() ) {
					GameOverResponse gameOver = new GameOverResponse(game.getGameID(), game.getWinner()
							.getUserName());
					gameOver.respond(game.getPlayer1().getPlayerThread().getClientSocket());
					gameOver.respond(game.getPlayer2().getPlayerThread().getClientSocket());
				}
				return new MakeMoveResponse(MakeMoveResponse.OK, gameID,
						username, row, col);
			}

		} catch (CellOwnershipException e) {
			return new MakeMoveResponse(MakeMoveResponse.FAIL, gameID,
					username, row, col);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MakeMoveRequest [type=" + type + ", gameID=" + gameID
				+ ", username=" + username + ", row=" + row + ", col=" + col
				+ "]";
	}

}
