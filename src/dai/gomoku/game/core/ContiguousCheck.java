package dai.gomoku.game.core;

/**
 * This interface is to be implemented by any class that checks for contiguous
 * cells.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public interface ContiguousCheck extends CellChangeObserver {
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
}
