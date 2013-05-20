package dai.gomoku.client.swing;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.GridLayout;

public class BoardPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4312994619651194802L;
	private static final int SIZE = 15;
	private BoardCell [][] boardCells;

	private GameModel model;
	private ClientController controller;

	public BoardPanel(ClientController controller, GameModel model) {
		this.controller = controller;
		this.model = model;
		initCells();
		addCells();
	}
	
	public void updateBoard() {
		for ( int row = 0; row < SIZE; row++ ) {
			for ( int col = 0; col < SIZE; col++ ) {
				boardCells[row][col].setText(""+model.getGameBoard()[row][col]);
			}
		}
	}
	
	private void initCells ( ) {
		boardCells = new BoardCell[SIZE][SIZE];
		for ( int row = 0; row < SIZE; row ++ ) {
			for ( int col = 0; col < SIZE; col++ ) {
				BoardCell aCell = new BoardCell(controller, row, col);
				aCell.setGameModel(model);
				aCell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				if ( (((row%2) == 0) && ((col%2) == 0)) || (((row%2) == 1) && ((col%2) == 1)) ) {
					aCell.setBackground(new Color(240, 240, 240));
				} else {
					aCell.setBackground(Color.WHITE);
				}
				boardCells[row][col] = aCell;
			}
		}
	}
	
	private void addCells ( ) {
		this.setLayout( new GridLayout(SIZE, SIZE) );
		for ( int row = 0; row < SIZE; row++ ) {
			for ( int col = 0; col < SIZE; col++ ) {
				this.add(boardCells[row][col]);
			}
		}
	}
}
