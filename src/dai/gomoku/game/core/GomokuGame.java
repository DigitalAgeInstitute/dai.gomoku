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

	private Board board;
	private Player player1;
	private Player player2;
	private boolean player1Turn;

	private AdjacencyCheckDiagonalDLUR dlurAdjacency;
	private AdjacencyCheckDiagonalULDR uldrAdjacency;
	private AdjacencyCheckLeftRight leftRightAdjacency;
	private AdjacencyCheckUpDown upDownAdjacency;

	private List<AdjacencyCheck> checkers;

	private boolean gameOver;
	private Player winner;

	public GomokuGame(int boardSize, Player player1, Player player2) {
		this.board = new Board(boardSize);
		this.player1 = player1;
		this.player2 = player2;
		this.player1Turn = true;
		this.gameOver = false;

		initAdjacecyCheckers();
	}

	public void markCell(Player player, int row, int col)
			throws CellOwnershipException {
		if (player1Turn) {
			if (player.equals(player1)) {
				board.getCellByPosition(row, col).setCellOwner(player);
				toggleTurn();
			}
		} else {
			if (player.equals(player2)) {
				board.getCellByPosition(row, col).setCellOwner(player);
				toggleTurn();
			}
		}

		checkForWin();
	}

	public Player getWinner() {
		return winner;
	}

	public boolean isGameOver() {
		return gameOver;
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
	}

	private void findAndSetWinner() {
		for (int i = 0; i < checkers.size(); i++) {
			if (checkers.get(i).hasWinner()) {
				winner = checkers.get(i).getWinner(WIN_NUM);
				// TODO: notify listeners (To be created) of game status change.
				break;
			}
		}
	}

}
