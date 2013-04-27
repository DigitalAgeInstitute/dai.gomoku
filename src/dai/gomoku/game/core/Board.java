package dai.gomoku.game.core;

/**
 * This class is used to create {@link Board} objects that are composed of
 * {@link Cell} objects. I also think this is a good place to initialise the
 * various {@link CellChangeObserver} objects that need to keep tabs on changes
 * in the cells.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class Board {
	private Cell[][] cells;
	private int size;

	public Board(int size) {
		this.size = size;
		initCells();
	}
	
	public int getSize ( ) {
		return size;
	}

	public Cell getCellByPosition(BoardPosition position) {
		return getCellByPosition(position.getRow(), position.getCol());
	}

	public Cell getCellByPosition(int row, int col) {
		return cells[row][col];
	}

	private void initCells() {
		this.cells = new Cell[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				cells[row][col] = new Cell(new BoardPosition(row, col));
			}
		}
	}

}
