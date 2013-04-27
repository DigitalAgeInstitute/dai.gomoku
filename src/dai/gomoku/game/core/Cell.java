package dai.gomoku.game.core;

import java.util.ArrayList;

public class Cell {
	private BoardPosition boardPosition;
	private Player cellOwner;
	private ArrayList<CellChangeObserver> cellChangeObservers;

	public Cell(BoardPosition position) {
		this.boardPosition = position;
	}

	/**
	 * @return the cellOwner
	 */
	public Player getCellOwner() {
		return cellOwner;
	}

	/**
	 * @param cellOwner
	 *            the cellOwner to set
	 */
	public void setCellOwner(Player cellOwner) throws CellOwnershipException {
		if ( cellOwner != null ) {
			throw new CellOwnershipException( "You tried to change Cell ownership. This is an illegal operation." );
		} else {
		this.cellOwner = cellOwner;
		notifyCellChangeObservers();
		}
	}

	/**
	 * @return the boardPosition
	 */
	public BoardPosition getBoardPosition() {
		return boardPosition;
	}
	
	public int getRow ( ) {
		return boardPosition.getRow();
	}
	
	public int getCol ( ) {
		return boardPosition.getCol();
	}

	public void registerCellChangeObserver(CellChangeObserver o) {
		this.cellChangeObservers.add(o);
	}

	public void removeCellChangeObserver(CellChangeObserver o) {
		this.cellChangeObservers.remove(o);
	}
	
	public void notifyCellChangeObservers ( ) {
		for ( CellChangeObserver observer : this.cellChangeObservers ) {
			observer.update( this );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boardPosition == null) ? 0 : boardPosition.hashCode());
		result = prime
				* result
				+ ((cellChangeObservers == null) ? 0 : cellChangeObservers
						.hashCode());
		result = prime * result
				+ ((cellOwner == null) ? 0 : cellOwner.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (boardPosition == null) {
			if (other.boardPosition != null)
				return false;
		} else if (!boardPosition.equals(other.boardPosition))
			return false;
		if (cellChangeObservers == null) {
			if (other.cellChangeObservers != null)
				return false;
		} else if (!cellChangeObservers.equals(other.cellChangeObservers))
			return false;
		if (cellOwner == null) {
			if (other.cellOwner != null)
				return false;
		} else if (!cellOwner.equals(other.cellOwner))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell [boardPosition=" + boardPosition + ", cellOwner="
				+ cellOwner + ", cellChangeObservers=" + cellChangeObservers
				+ "]";
	}

}
