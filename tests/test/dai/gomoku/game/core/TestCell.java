package test.dai.gomoku.game.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dai.gomoku.game.core.BoardPosition;
import dai.gomoku.game.core.Cell;
import dai.gomoku.game.core.CellOwnershipException;
import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

public class TestCell {
	private Cell cell1;
	private Cell cell2;

	@Before
	public void setUp() throws Exception {
		cell1 = new Cell(new BoardPosition(0, 0));
		cell2 = new Cell(new BoardPosition(1, 2));
	}

	@Test
	public void testCell() {
		Cell testCell = new Cell(new BoardPosition(9, 10));
		assertNotNull(testCell);
	}

	@Test
	public void testGetCellOwner() {
		Player player = new HumanPlayer("1", "one", "The", "One");
		markCell(cell2, player);
		assertEquals(cell2.getCellOwner(), new HumanPlayer("1", "one", "The",
				"One"));
	}

	@Test
	public void testSetCellOwner() {
		Player player = new HumanPlayer("2", "two", "The", "Other");
		Player player2 = new HumanPlayer("3", "three", "The", "Third");
		boolean failed = false;
		try {
			cell1.setCellOwner(player);
			cell1.setCellOwner(player2);
		} catch (CellOwnershipException e) {
			failed = true;
		}
		
		assertNotNull(player);
		assertNotNull(cell1.getCellOwner());
		assertEquals(cell1.getCellOwner(), player);
		assertTrue(failed);
	}

	@Test
	public void testGetBoardPosition() {
		Cell testCell = new Cell( new BoardPosition(0, 0));
		BoardPosition boardPosition = new BoardPosition(0, 0);
		assertEquals(testCell.getBoardPosition(), boardPosition);
	}

	@Test
	public void testGetRow() {
		Cell testCell = new Cell( new BoardPosition(5, 9));
		assertEquals(testCell.getRow(), 5);
	}

	@Test
	public void testGetCol() {
		Cell testCell = new Cell( new BoardPosition(6, 7));
		assertEquals(testCell.getCol(), 7);
	}

	@Test
	public void testEqualsObject() {
		Cell similarToCell1 = new Cell(new BoardPosition(0, 0));
		assertTrue(cell1.equals(similarToCell1));
		
		markCell(cell1, (Player) new HumanPlayer("2", "two", "The", "Other"));
		assertFalse(cell1.equals(similarToCell1));
		
		markCell(similarToCell1, (Player) new HumanPlayer("2", "two", "The", "Other"));
		assertTrue(cell1.equals(similarToCell1));
	}

	private void markCell(Cell cell, Player player) {
		try {
			cell.setCellOwner(player);
		} catch (CellOwnershipException e) {
			e.printStackTrace();
		}
	}

}
