package dai.gomoku.game.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the game itself. As is, it's like a state machine of sorts, that
 * switches among various states of being dependent on external actions.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class GomokuGame {
	private static int WIN_NUM = 5;

	private String gameID;
	private Board board;
	private Player player1;
	private Player player2;
	private boolean player1Turn;

	private AdjacencyCheckDiagonalDLUR dlurAdjacency;
	private AdjacencyCheckDiagonalULDR uldrAdjacency;
	private AdjacencyCheckLeftRight leftRightAdjacency;
	private AdjacencyCheckUpDown upDownAdjacency;

	private List<AdjacencyCheck> checkers;

	private List<GameWinListener> winListeners;

	private boolean gameOver;
	private Player winner;

	public GomokuGame(int boardSize, Player player1, Player player2) {
		this.gameID = "GM:" + System.currentTimeMillis();
		this.board = new Board(boardSize);
		this.player1 = player1;
		this.player2 = player2;
		this.player1Turn = true;
		this.gameOver = false;

		this.winListeners = new ArrayList<GameWinListener>();

		initAdjacecyCheckers();
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
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @param player1
	 *            the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @param player2
	 *            the player2 to set
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public void markCell(Player player, int row, int col)
			throws CellOwnershipException {
		if (!isGameOver()) {
			if (player1Turn) {
				if (player.equals(player1)) {
					board.getCellByPosition(row, col).setCellOwner(player);
					toggleTurn();
				} else {
					throw new CellOwnershipException("Not your turn");
				}
			} else {
				if (player.equals(player2)) {
					board.getCellByPosition(row, col).setCellOwner(player);
					toggleTurn();
				} else {
					throw new CellOwnershipException("Not your turn");
				}
			}
			checkForWin();
		}

	}

	public Player getWinner() {
		return winner;
	}

	public Player getLoser() {
		if (winner == player1) {
			return player2;
		} else {
			return player1;
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void registerGameWinListener(GameWinListener listener) {
		this.winListeners.add(listener);
	}

	public void deregisterGameWinListener(GameWinListener listener) {
		this.winListeners.add(listener);
	}

	private void initAdjacecyCheckers() {
		this.dlurAdjacency = new AdjacencyCheckDiagonalDLUR(this.board);
		this.uldrAdjacency = new AdjacencyCheckDiagonalULDR(this.board);
		this.leftRightAdjacency = new AdjacencyCheckLeftRight(this.board);
		this.upDownAdjacency = new AdjacencyCheckUpDown(this.board);

		this.checkers = new ArrayList<AdjacencyCheck>();
		this.checkers.add(dlurAdjacency);
		this.checkers.add(leftRightAdjacency);
		this.checkers.add(uldrAdjacency);
		this.checkers.add(upDownAdjacency);
	}

	private void toggleTurn() {
		player1Turn = (!player1Turn);
	}

	private void checkForWin() {
		if (dlurAdjacency.checkContiguous(WIN_NUM)
				|| uldrAdjacency.checkContiguous(WIN_NUM)
				|| leftRightAdjacency.checkContiguous(WIN_NUM)
				|| upDownAdjacency.checkContiguous(WIN_NUM)) {
			gameOver = true;
			findAndSetWinner();
		}

		System.out.println("\tup-down:" + upDownAdjacency);
		System.out.println("\tleft-right:" + leftRightAdjacency);
		System.out.println("\tupleft-downright:" + uldrAdjacency);
		System.out.println("\tdownLeft-upRight:" + dlurAdjacency);
	}

	private void findAndSetWinner() {
		for (int i = 0; i < checkers.size(); i++) {
			if (checkers.get(i).hasWinner()) {
				winner = checkers.get(i).getWinner(WIN_NUM);
				notifyWinListeners();
				break;
			}
		}
	}

	private void notifyWinListeners() {
		for (GameWinListener listener : winListeners) {
			listener.gameIsOver(this);
		}
	}

}
