package test.dai.gomoku.game.core;

import static org.junit.Assert.*;

import org.junit.Test;

import dai.gomoku.game.core.Board;
import dai.gomoku.game.core.BoardPosition;
import dai.gomoku.game.core.Cell;

public class TestBoard {
	private final int SIZE = 19;
	private Board board = null;
	private Cell cell = null;
	
	@Test
	public void testBoard() {
		assertNull(board);
		board = new Board(SIZE);
		assertNotNull(board);
	}

	@Test
	public void testGetSize() {
		board = new Board(SIZE);
		assertEquals(19, board.getSize());
	}

	@Test
	public void testGetCellByPositionBoardPosition() {
		cell = new Cell(new BoardPosition(16, 16));
		assertNotNull(cell);
		board = new Board(SIZE);
		BoardPosition position = new BoardPosition(16, 16);
		assertEquals(cell, board.getCellByPosition(position));
	}

	@Test
	public void testGetCellByPositionIntInt() {
		cell = new Cell(new BoardPosition(17, 2));
		assertNotNull(cell);
		board = new Board(SIZE);
		assertEquals(cell, board.getCellByPosition(17, 2));
	}

}
