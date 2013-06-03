package test.dai.gomoku.game.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dai.gomoku.game.core.AdjacencyCheckLeftRight;
import dai.gomoku.game.core.Board;
import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

public class TestAdjacencyCheckLeftRight {
	private final int SIZE = 19;
	Board board;
	AdjacencyCheckLeftRight leftRight;
	Player player1;
	Player player2;

	@Before
	public void setup() {
		board = new Board(SIZE);
		leftRight = new AdjacencyCheckLeftRight(board);
		player1 = new HumanPlayer(1, "one", "The", "One");
		player2 = new HumanPlayer(2, "two", "The", "Other");
	}

	@Test
	public void testAdjacencyCheckLeftRight() {
		AdjacencyCheckLeftRight test = null;
		assertNull(test);
		test = new AdjacencyCheckLeftRight(board);
		assertNotNull(test);
	}

	@Test
	public void testUpdate() {
		TestUtilities.markBoard(board, player1, 5, 3);
		leftRight.update(board.getCellByPosition(5, 3));
		assertFalse(leftRight.checkContiguous(5));
	}

	@Test
	public void testCheckContiguous() {
		int row = 9;
		TestUtilities.markBoard(board, player2, row, 8);
		TestUtilities.markBoard(board, player2, row, 9);
		TestUtilities.markBoard(board, player2, row, 10);
		TestUtilities.markBoard(board, player2, row, 11);
		TestUtilities.markBoard(board, player2, row, 12);
		assertTrue(leftRight.checkContiguous(5));
	}

}
