package test.dai.gomoku.game.core;

import static org.junit.Assert.fail;
import dai.gomoku.game.core.Board;
import dai.gomoku.game.core.CellOwnershipException;
import dai.gomoku.game.core.Player;

public class TestUtilities {
	public static void markBoard( Board board, Player player, int row, int col ) {
		try {
			board.getCellByPosition(row, col).setCellOwner(player);
		} catch (CellOwnershipException e) {
			e.printStackTrace();
			fail("Cell already owned!");
		}
	}
}
