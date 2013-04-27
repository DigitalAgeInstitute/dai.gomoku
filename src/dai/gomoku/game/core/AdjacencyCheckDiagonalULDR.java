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
public class AdjacencyCheckDiagonalULDR implements ContiguousCheck {
	private Board board;
	private List<Set<Cell>> uldrAdjacecies;

	public AdjacencyCheckDiagonalULDR(Board board) {
		this.board = board;
		uldrAdjacecies = new ArrayList<Set<Cell>>();
	}

	@Override
	public void update(Cell cell) {
		checkAdjacencies(cell);
	}

	@Override
	public boolean checkContiguous(int num) {
		boolean present = false;
		for (int i = 0; i < uldrAdjacecies.size(); i++) {
			if (uldrAdjacecies.get(i).size() == num) {
				present = true;
				break;
			}
		}

		return present;
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
			if ( cellToCheck.getCellOwner().equals(cellOwner) ) {
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
			if ( cellToCheck.getCellOwner().equals(cellOwner) ) {
				adjacentCells.add(cellToCheck);
				row++;
				col++;
			} else {
				break;
			}
		}
	}
	
	private void sanitiseAndAdd ( Set<Cell> adjacentCells ) {
		List<Set<Cell>> toRemove = addAndReturnSubSets( adjacentCells );
		removeSubSets( toRemove );
	}
	
	private List<Set<Cell>> addAndReturnSubSets ( Set<Cell> toAdd ) {
		List<Set<Cell>> subSets = new ArrayList<Set<Cell>>();
		for ( int i = 0; i < uldrAdjacecies.size(); i++ ) {
			for ( Iterator<Cell> it = toAdd.iterator(); it.hasNext(); ) {
				if ( uldrAdjacecies.get(i).contains(it.next()) ) {
					toAdd.addAll( uldrAdjacecies.get(i) );
					subSets.add(uldrAdjacecies.get(i));
					break;
				}
			}
		}
		
		uldrAdjacecies.add(toAdd);
		return subSets;
	}
	
	private void removeSubSets ( List<Set<Cell>> toRemove ) {
		for ( int i = 0; i < toRemove.size(); i++ ) {
			uldrAdjacecies.remove( toRemove.get(i) );
		}
	}

}