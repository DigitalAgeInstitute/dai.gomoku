package dai.gomoku.game.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdjacencyCheckDiagonalDLUR implements ContiguousCheck {
	private Board board;
	private List<Set<Cell>> dlurAdjacencies;

	public AdjacencyCheckDiagonalDLUR(Board board) {
		this.board = board;
		dlurAdjacencies = new ArrayList<Set<Cell>>();
		registerWithCells();
	}

	@Override
	public void update(Cell cell) {
		checkAdjacencies(cell);
	}

	@Override
	public boolean checkContiguous(int num) {
		boolean present = false;
		for (int i = 0; i < dlurAdjacencies.size(); i++) {
			if (dlurAdjacencies.get(i).size() == num) {
				present = true;
				break;
			}
		}
		return present;
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
		checkDownLeft(cell, adjacentCells);
		checkUpRight(cell, adjacentCells);
		if (adjacentCells.size() > 1) {
			sanitiseAndAdd(adjacentCells);
		}
	}

	private void checkDownLeft(Cell cell, Set<Cell> adjacentCells) {
		int row = cell.getRow() + 1;
		int col = cell.getCol() - 1;
		Player cellOwner = cell.getCellOwner();
		while ((row >= 0) && (col >= 0) && (row < board.getSize())
				&& (col < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(row, col);
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjacentCells.add(cellToCheck);
				row++;
				col--;
			} else {
				break;
			}
		}
	}

	private void checkUpRight(Cell cell, Set<Cell> adjacentCells) {
		int row = cell.getRow() - 1;
		int col = cell.getCol() + 1;
		Player cellOwner = cell.getCellOwner();
		while ((row >= 0) && (col >= 0) && (row < board.getSize())
				&& (col < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(row, col);
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjacentCells.add(cellToCheck);
				row--;
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
		for (int i = 0; i < dlurAdjacencies.size(); i++) {
			for (Iterator<Cell> it = toAdd.iterator(); it.hasNext();) {
				if (dlurAdjacencies.get(i).contains(it.next())) {
					toAdd.addAll(dlurAdjacencies.get(i));
					subSets.add(dlurAdjacencies.get(i));
					break;
				}
			}
		}

		dlurAdjacencies.add(toAdd);
		return subSets;
	}

	private void removeSubSets(List<Set<Cell>> toRemove) {
		for (int i = 0; i < toRemove.size(); i++) {
			dlurAdjacencies.remove(toRemove.get(i));
		}
	}

}
