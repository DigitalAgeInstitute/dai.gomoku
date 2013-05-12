package dai.gomoku.client.swing;

import java.util.*;

public class HintsImplementation {
	char renjuBoard [][] = new char [15][15];
	char[][] findForOneCharacter(char [][] board, char xORo) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] == xORo) {
					renjuBoard[i][j] = xORo;
				}
				else {
					renjuBoard[i][j] = 'Z';
				}
			}
		}
		return renjuBoard;		
	}

	// Generate an array of char's, 15 x 15, to put in the X's and O's
	//
	// This could be a would-be board definition. I’ve used a 15 x 15 char array. Any other thing 
	// including a more intelligent board (where each element knows itself and its neighbours) is OK

	 // Get this declaration right, syntactically if it has an error...

	Set<String> xAndY = new HashSet<String>();
	int howManyInARow = 0;
	// Here that board above is used for the parameter... 
	public Set<String> generateHint (char [][] board, char xORo) {
	   int countX, countY, i, step;
	   for (countX = 0; countX < 15; countX++) {
	      for (countY = 0; countY < 15; countY++) {
	         // explanatory: Here we have something running through each coordinate field i the array.
	         // Now, from each point, we'll check all directions for 5 in a row...
	         // This algorithm will be brute force - no optimizations. However, its not a big deal, 
	         // the analysis and depth will be really taking its toll, not so much analyzing if someone has won.
	         int dir [] = new int [8]; // create a temporary array of 8 directions to count up successive equals of a kind
	         int dirx [] = {0,1,1,1,0,-1,-1,-1}; // These are counts for X, for various directions 0 to 7
	         int diry [] = {1,1,0,-1,-1,-1,0,1}; // These are counts for Y, for various directions 0 to 7
	         for (i = 0; i < 8; i++)
	            dir[i] = 0; // reset counters to zero for all directions.
	            
	         // Definition: North = direction 0, all other directions clockwise up to North-West.
	         // Definition: Board oriented like cartesian coordinates, X + towards right (EAST), Y + towards NORTH (up)
	         
	         // Now, run through all directions and count how many in a row in either of 8 directions from the onSpot value
	         char onSpot = board[countX][countY];
	         
	         if (onSpot == xORo) {            
	            // For each direction...
	            for (i = 0; i < 8; i++) {
	               int xc, yc;
	               // Now take 4 steps (maximum) in a given direction, and return if not same as onSpot, or if outside board.
	               for (step = 1; step <= 4; step++) {
	                  xc = countX + step*dirx[i]; // create the x coordinate of the next field to check
	                  yc = countY + step*diry[i]; // create the y coordinate of the next field to check
	                  xAndY.add(xc + "," + yc);
	                  // Check if resulting coordinate is within the board. If not, skip.
	                  if (xc >= 0 && xc <= 14 && yc >= 0 && yc <= 14) { 
	                     if (board[xc][yc] != onSpot)
	                        break; // skip the loop, as a different flavour was found (either empty or opponent)
	                  } else
	                     break; // skip the loop, continue next direction...               
	               }
	               if (step > 2) { // counted to the end, while within board and within same flavour... 5 in a row.
	                  // HERE you have 5 in a row... Ranging from countX,countY up to countX+dirx*4, countY+diry*4, direction = i
	            	  System.out.println(step + " in a row");
	            	  howManyInARow = step;
	            	  for (String s : xAndY) {
						System.out.println(s);
					}
	               } 
	            }         
	         }
	      }
	   }
	return xAndY;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
