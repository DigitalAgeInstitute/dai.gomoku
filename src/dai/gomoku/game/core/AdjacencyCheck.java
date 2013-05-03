package dai.gomoku.game.core;

/**
 * This interface is to be implemented by any class that checks for contiguous
 * cells.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public interface AdjacencyCheck extends CellChangeObserver {
	/**
	 * This method checks for num contiguous {@link Cell} objects. Cells are
	 * contiguous if and only if they are next to each other and are owned by
	 * the same {@link Player} object.
	 * 
	 * @param num
	 *            The number of contiguous cells to check for.
	 * 
	 * @return <code>true</code> if the there are num contiguous cells and
	 *         <code>false</code> otherwise.
	 */
	boolean checkContiguous(final int num);

	/**
	 * Checks if the adjacency checker is the one containing the winning
	 * adjacency
	 * 
	 * @return <code>true</true> if the adjacency checker has the winning adjacency, otherwise, <code>false</code>
	 */
	boolean hasWinner();

	/**
	 * Gets the winning player.
	 * 
	 * @param win_size
	 *            The size to check for win.
	 * @return The wining {@link Player} or null if none
	 */
	Player getWinner(int win_size);
}
