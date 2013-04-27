package dai.gomoku.game.core;

/**
 * This interface enables the creation of observers that respond to any changes
 * in the cell. Any object that requires to act whenever a change in a cell
 * occurs will need to implement this interface in order for it to be informed
 * of any changes.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public interface CellChangeObserver {
	/**
	 * This method is invoked to tell the {@link CellChangeObserver} objects to
	 * update their status depending on the change that the {@link Cell} object
	 * referred to has experienced.
	 * 
	 * @param cell
	 *            A reference to the changed cell.
	 */
	void update(Cell cell);
}
