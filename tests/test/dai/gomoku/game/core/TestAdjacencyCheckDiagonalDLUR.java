package test.dai.gomoku.game.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dai.gomoku.game.core.AdjacencyCheckDiagonalDLUR;
import dai.gomoku.game.core.Board;
import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

public class TestAdjacencyCheckDiagonalDLUR {
	private final int SIZE = 19;
	Board board;
	AdjacencyCheckDiagonalDLUR dlur;
	Player player;

	@Before
	public void setUp() throws Exception {
		board = new Board(SIZE);
		dlur = new AdjacencyCheckDiagonalDLUR(board);
		player = new HumanPlayer(1, "theone", "The", "One");
	}

	@Test
	public void testAdjacencyCheckDiagonalDLUR() {
		AdjacencyCheckDiagonalDLUR test = null;
		assertNull(test);
		test = new AdjacencyCheckDiagonalDLUR(board);
		assertNotNull(test);
	}

	@Test
	public void testUpdate() {
		// Is it necessary to test this?
	}

	@Test
	public void testCheckContiguous() {
		TestUtilities.markBoard(board, player, 13, 9);
		assertFalse(dlur.checkContiguous(5));
		TestUtilities.markBoard(board, player, 12, 10);
		TestUtilities.markBoard(board, player, 11, 11);
		assertFalse(dlur.checkContiguous(5));
		TestUtilities.markBoard(board, player, 10, 12);
		TestUtilities.markBoard(board, player, 9, 13);
		assertTrue(dlur.checkContiguous(5));
	}

}
