package dai.gomoku.game.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Checks cell adjacency in the Up-and-down direction
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class AdjacencyCheckUpDown implements ContiguousCheck {
	private Board board;
	private List<Set<Cell>> upDownAdjacencies;

	public AdjacencyCheckUpDown(Board board) {
		this.board = board;
		upDownAdjacencies = new ArrayList<Set<Cell>>();
		registerWithCells();
	}

	@Override
	public void update(Cell cell) {
		checkAdjacencies(cell);
	}

	@Override
	public boolean checkContiguous(final int num) {
		boolean present = false;
		for (int i = 0; i < upDownAdjacencies.size(); i++) {
			if (upDownAdjacencies.get(i).size() == num) {
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
		Set<Cell> adjacentCells = new HashSet<>();
		adjacentCells.add(cell);
		checkUp(cell, adjacentCells);
		checkDown(cell, adjacentCells);
		if (adjacentCells.size() > 1) {
			sanitiseAndAdd(adjacentCells);
		}
	}

	private void checkUp(Cell cell, Set<Cell> adjacentCells) {
		int row = cell.getRow() - 1;
		Player cellOwner = cell.getCellOwner();

		while ((row >= 0) && (row < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(row, cell.getCol());
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjacentCells.add(cellToCheck);
				row--;
			} else {
				break;
			}
		}
	}

	private void checkDown(Cell cell, Set<Cell> adjacentCells) {
		int row = cell.getRow() + 1;
		Player cellOwner = cell.getCellOwner();

		while ((row >= 0) && (row < board.getSize())) {
			Cell cellToCheck = board.getCellByPosition(row, cell.getCol());
			if (cellOwner.equals(cellToCheck.getCellOwner())) {
				adjacentCells.add(cellToCheck);
				row++;
			} else {
				break;
			}
		}
	}

	private void sanitiseAndAdd(Set<Cell> adjacentCells) {
		List<Set<Cell>> toRemove = addAndReturnSubSets(adjacentCells);
		removeSubSets(toRemove);
	}

	private List<Set<Cell>> addAndReturnSubSets(Set<Cell> adjacentCells) {
		List<Set<Cell>> subSets = new ArrayList<Set<Cell>>();
		for (int i = 0; i < upDownAdjacencies.size(); i++) {
			for (Iterator<Cell> it = adjacentCells.iterator(); it.hasNext();) {
				if (upDownAdjacencies.get(i).contains(it.next())) {
					subSets.add(upDownAdjacencies.get(i));
					adjacentCells.addAll(upDownAdjacencies.get(i));
					break;
				}
			}
		}

		upDownAdjacencies.add(adjacentCells);
		return subSets;
	}

	private void removeSubSets(List<Set<Cell>> toRemove) {
		for (int i = 0; i < toRemove.size(); i++) {
			upDownAdjacencies.remove(toRemove.get(i));
		}
	}

}
