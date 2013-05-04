package dai.gomoku.client.android;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

public class FindWhoWins {

	int i, step, counter =0;
	Boolean winnerFoundNowExit =false;

	// declaring of variables
	// countX stand for the x-axis
	// countY stands for the y-axis
	// step stand for the steps made in a particular direction

	public FindWhoWins(ArrayList<TextView> arrTextViews, Context cntx) {
		// TODO Auto-generated constructor stub
		
		int dirX[] = { -1, -1, -1, 0, 0, 1, 1, 1 }; // this are the
		// possibilities
		// around x
		int dirY[] = { -1, 0, 1, -1, 1, -1, 0, 1 };// this are the
		// possibilities
		// around x
		
		for (int countX = 0; countX < 15; countX++) {
			for (int countY = 0; countY < 15; countY++) {
				
				String onSpot = arrTextViews.get((countX*15)+countY).getText().toString();
				
				//at this point we get what is in the coordinates related to countX and countY and assign it to the onSpot string
				//this can either be X or O or a space
				
				if (onSpot.equals("X") || onSpot.equals("O")){ // this here checks what the string onSpot is set to if either X or O
					Toast.makeText(cntx, onSpot, Toast.LENGTH_SHORT).show();
					for (i = 0; i < 8; i++) {// at this point it loop through all the possible ways around onSpot
						int xc, yc;

						for (step = 1; step <= 4; step++) {
							xc = countX + (step * dirX[i]); // here xc is set to a possible point that can occur 
							yc = countY + (step * dirY[i]); // here yc is set to a possible point that can occur 
							
							
							if (xc >-1 && xc < 15 && yc >-1 && yc <15) {
								if (!onSpot.equals(arrTextViews.get((xc*15)+yc).getText().toString())) {									
									arrTextViews.get((xc*15)+yc).setBackgroundColor(Color.RED);
									break;
								} else {
									arrTextViews.get((countX*15)+countY).setBackgroundColor(Color.YELLOW);
								}
							} else {
								break;
							}
						}
						if (step == 5) {
							Toast.makeText(cntx, "step: 5 called", Toast.LENGTH_SHORT).show();
							new AlertDialog.Builder(cntx).
							setTitle("winner is found").
							setMessage("player X wins the game").
							setNeutralButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									
								}
							}).show();
							winnerFoundNowExit = true;
						}
					}
					
				}
				
				
				
			}
			if(winnerFoundNowExit==true){
				winnerFoundNowExit =false;
				break;
			}
		}
		// this here will loop through the different coordinates on our UI
	}
}
