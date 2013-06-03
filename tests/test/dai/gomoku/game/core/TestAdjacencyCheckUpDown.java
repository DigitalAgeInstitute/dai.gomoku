package test.dai.gomoku.game.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dai.gomoku.game.core.AdjacencyCheckUpDown;
import dai.gomoku.game.core.Board;
import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

public class TestAdjacencyCheckUpDown {
	private final int SIZE = 19;
	Board board;
	AdjacencyCheckUpDown upDown;
	Player player1;
	Player player2;
	
	@Before
	public void setUp() throws Exception {
		board = new Board(SIZE);
		upDown = new AdjacencyCheckUpDown(board);
		player1 = new HumanPlayer(1, "one", "The", "One");
		player2 = new HumanPlayer(2, "two", "The", "Other");
	}

	@Test
	public void testAdjacencyCheckUpDown() {
		AdjacencyCheckUpDown test = new AdjacencyCheckUpDown(board);
		assertNotNull(test);
	}

	@Test
	public void testUpdate() {
		TestUtilities.markBoard(board, player1, 0, 0);
		upDown.update( board.getCellByPosition(0, 0) );
		assertFalse( upDown.checkContiguous(5) );
	}

	@Test
	public void testCheckContiguous() {
		TestUtilities.markBoard(board, player2, 0, 1);
		TestUtilities.markBoard(board, player2, 1, 1);
		TestUtilities.markBoard(board, player2, 2, 1);
		TestUtilities.markBoard(board, player2, 3, 1);
		TestUtilities.markBoard(board, player2, 4, 1);
		assertTrue( upDown.checkContiguous(5));
	}
	
	

}
