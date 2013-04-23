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
	void update();
}
