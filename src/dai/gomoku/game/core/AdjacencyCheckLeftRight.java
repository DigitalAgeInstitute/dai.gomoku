package dai.gomoku.game.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Checks cell adjacency in the Left-to-right direction.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class AdjacencyCheckLeftRight implements AdjacencyCheck {
	private Board board;
	private List<Set<Cell>> leftRightAdjacencies;
	private Player winner;
	private boolean hasWinner;

	public AdjacencyCheckLeftRight(Board board) {
		this.board = board;
		leftRightAdjacencies = new ArrayList<Set<Cell>>();
		registerWithCells();
	}

	@Override
	public void update(Cell cell) {
		checkAdjacencies(cell);
	}

	@Override
	public boolean checkContiguous(int num) {
		boolean present = false;
		for (int i = 0; i < leftRightAdjacencies.size(); i++) {
			if (leftRightAdjacencies.get(i).size() == num) {
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
		Iterator<Cell> it = leftRightAdjacencies.get(index).iterator();
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
		checkLeft(cell, adjacentCells);
		checkRight(cell, adjacentCells);
		if (adjacentCells.size() > 1) {
			sanitiseAndAdd(adjacentCells);
		}
	}

	private void checkLeft(Cell cell, Set<Cell> adjCells) {
		int col = cell.getBoardPosition().getCol() - 1;
		Player cellOwner = cell.getCellOwner();

		while ((col >= 0) && (col < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(cell.getBoardPosition()
					.getRow(), col);
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjCells.add(cellToCheck);
				col--;
			} else {
				break;
			}
		}
	}

	private void checkRight(Cell cell, Set<Cell> adjCells) {
		int col = cell.getBoardPosition().getCol() + 1;
		Player cellOwner = cell.getCellOwner();

		while ((col >= 0) && (col < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(cell.getBoardPosition()
					.getRow(), col);
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjCells.add(cellToCheck);
				col++;
			} else {
				break;
			}
		}
	}

	private void sanitiseAndAdd(Set<Cell> adjacentCells) {
		List<Set<Cell>> subSets = addAndReturnSubSets(adjacentCells);
		removeSubSets(subSets);
	}

	private List<Set<Cell>> addAndReturnSubSets(Set<Cell> adjacentCells) {
		List<Set<Cell>> subSets = new ArrayList<Set<Cell>>();
		for (int i = 0; i < leftRightAdjacencies.size(); i++) {
			for (Iterator<Cell> it = adjacentCells.iterator(); it.hasNext();) {
				if (leftRightAdjacencies.get(i).contains(it.next())) {
					adjacentCells.addAll(leftRightAdjacencies.get(i));
					subSets.add(leftRightAdjacencies.get(i));
					break;
				}
			}
		}

		leftRightAdjacencies.add(adjacentCells);
		return subSets;
	}

	private void removeSubSets(List<Set<Cell>> toRemove) {
		for (int i = 0; i < toRemove.size(); i++) {
			leftRightAdjacencies.remove(toRemove.get(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdjacencyCheckLeftRight [leftRightAdjacencies="
				+ leftRightAdjacencies + "]";
	}

}
