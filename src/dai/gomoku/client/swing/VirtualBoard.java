package dai.gomoku.client.swing;

import java.io.Serializable;
import java.util.*;

public class VirtualBoard implements Serializable {
	
	Map<String, HashSet<List<Integer>>> gameMap = new HashMap<String, HashSet<List<Integer>>>();
	HashSet<List<Integer>> occupiedCells = new HashSet<List<Integer>>();
	char [][] board = new char[15][15];
	
}
