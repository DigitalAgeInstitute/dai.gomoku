package dai.gomoku.game.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Checks cell adjacency in the diagonal direction Up-Left-Down-Right
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class AdjacencyCheckDiagonalULDR implements AdjacencyCheck {
	private Board board;
	private List<Set<Cell>> uldrAdjacencies;
	private Player winner;
	private boolean hasWinner;

	public AdjacencyCheckDiagonalULDR(Board board) {
		this.board = board;
		uldrAdjacencies = new ArrayList<Set<Cell>>();
		registerWithCells();
	}

	@Override
	public void update(Cell cell) {
		checkAdjacencies(cell);
	}

	@Override
	public boolean checkContiguous(int num) {
		boolean present = false;
		for (int i = 0; i < uldrAdjacencies.size(); i++) {
			if (uldrAdjacencies.get(i).size() == num) {
				present = true;
				hasWinner = true;
				winner = retrieveWinner(i);
				break;
			}
		}

		return present;
	}

	@Override
	public boolean hasWinner() {
		return hasWinner;
	}

	@Override
	public Player getWinner(int win_size) {
		return winner;
	}

	private Player retrieveWinner(int index) {
		Iterator<Cell> it = uldrAdjacencies.get(index).iterator();
		Player toReturn = null;
		for (; it.hasNext();) {
			toReturn = ((Cell) it.next()).getCellOwner();
			break;
		}
		return toReturn;
	}

	private void registerWithCells() {
		int boardSize = board.getSize();
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				board.getCellByPosition(row, col).registerCellChangeObserver(
						this);
			}
		}
	}

	private void checkAdjacencies(Cell cell) {
		Set<Cell> adjacentCells = new HashSet<Cell>();
		adjacentCells.add(cell);
		checkUpLeft(cell, adjacentCells);
		checkDownRight(cell, adjacentCells);
		if (adjacentCells.size() > 1) {
			sanitiseAndAdd(adjacentCells);
		}
	}

	private void checkUpLeft(Cell cell, Set<Cell> adjacentCells) {
		int row = cell.getRow() - 1;
		int col = cell.getCol() - 1;
		Player cellOwner = cell.getCellOwner();
		while ((row >= 0) && (col >= 0) && (row < board.getSize())
				&& (col < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(row, col);
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjacentCells.add(cellToCheck);
				row--;
				col--;
			} else {
				break;
			}
		}
	}

	private void checkDownRight(Cell cell, Set<Cell> adjacentCells) {
		int row = cell.getRow() + 1;
		int col = cell.getCol() + 1;
		Player cellOwner = cell.getCellOwner();
		while ((row >= 0) && (col >= 0) && (row < board.getSize())
				&& (col < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(row, col);
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjacentCells.add(cellToCheck);
				row++;
				col++;
			} else {
				break;
			}
		}
	}

	private void sanitiseAndAdd(Set<Cell> adjacentCells) {
		List<Set<Cell>> toRemove = addAndReturnSubSets(adjacentCells);
		removeSubSets(toRemove);
	}

	private List<Set<Cell>> addAndReturnSubSets(Set<Cell> toAdd) {
		List<Set<Cell>> subSets = new ArrayList<Set<Cell>>();
		for (int i = 0; i < uldrAdjacencies.size(); i++) {
			for (Iterator<Cell> it = toAdd.iterator(); it.hasNext();) {
				if (uldrAdjacencies.get(i).contains(it.next())) {
					toAdd.addAll(uldrAdjacencies.get(i));
					subSets.add(uldrAdjacencies.get(i));
					break;
				}
			}
		}

		uldrAdjacencies.add(toAdd);
		return subSets;
	}

	private void removeSubSets(List<Set<Cell>> toRemove) {
		for (int i = 0; i < toRemove.size(); i++) {
			uldrAdjacencies.remove(toRemove.get(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdjacencyCheckDiagonalULDR [uldrAdjacencies=" + uldrAdjacencies
				+ "]";
	}

}
