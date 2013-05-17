package dai.gomoku.client.swing;

import java.util.ArrayList;
import java.util.List;

/**
 * The UI game model
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class GameModel {
	private String gameID;
	private String playerX;
	private String playerO;

	private final int BOARD_SIZE = 15;
	private char[][] gameBoard;
	private List<GameBoardChangeListener> gameBoardChangeListeners;

	public GameModel() {
		initCells();
		gameBoardChangeListeners = new ArrayList<GameBoardChangeListener>();
	}

	/**
	 * @return the gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * @return the playerX
	 */
	public String getPlayerX() {
		return playerX;
	}

	/**
	 * @return the playerO
	 */
	public String getPlayerO() {
		return playerO;
	}

	/**
	 * @return the gameBoard
	 */
	public char[][] getGameBoard() {
		return gameBoard;
	}

	public void addGameBoardChangeListener(GameBoardChangeListener listener) {
		gameBoardChangeListeners.add(listener);
	}

	public void removeGameBoardChangeListener(GameBoardChangeListener listener) {
		if (gameBoardChangeListeners.contains(listener)) {
			gameBoardChangeListeners.remove(listener);
		}
	}

	public void markCell(int row, int col, String username) {
		if (username.equals(playerO)) {
			gameBoard[row][col] = 'O';
		} else {
			gameBoard[row][col] = 'X';
		}
		notifyGameBoardChangeListeners();
	}

	private void initCells() {
		gameBoard = new char[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				gameBoard[row][col] = ' ';
			}
		}
	}

	private void notifyGameBoardChangeListeners() {
		for (int i = 0; i < gameBoardChangeListeners.size(); i++) {
			gameBoardChangeListeners.get(i).updateBoard();
		}
	}

}
