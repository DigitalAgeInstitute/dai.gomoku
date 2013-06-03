package test.dai.gomoku.game.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dai.gomoku.game.core.AdjacencyCheckDiagonalULDR;
import dai.gomoku.game.core.Board;
import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

public class TestAdjacencyCheckDiagonalULDR {
	private final int SIZE = 19;
	Board board;
	AdjacencyCheckDiagonalULDR uldr;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		board = new Board(SIZE);
		uldr = new AdjacencyCheckDiagonalULDR(board);
		player = new HumanPlayer(1, "one", "The", "One");
	}

	@Test
	public void testAdjacencyCheckDiagonalULDR() {
		AdjacencyCheckDiagonalULDR test = null;
		assertNull(test);
		test = new AdjacencyCheckDiagonalULDR(board);
		assertNotNull(test);
	}

	@Test
	public void testUpdate() {
		TestUtilities.markBoard(board, player, 9, 7);
		uldr.update(board.getCellByPosition(9, 7));
		assertFalse( uldr.checkContiguous(5) );
	}

	@Test
	public void testCheckContiguous() {
		TestUtilities.markBoard(board, player, 7, 7);
		TestUtilities.markBoard(board, player, 8, 8);
		TestUtilities.markBoard(board, player, 9, 9);
		TestUtilities.markBoard(board, player, 10, 10);
		TestUtilities.markBoard(board, player, 11, 11);
		assertTrue( uldr.checkContiguous(5));
	}

}
