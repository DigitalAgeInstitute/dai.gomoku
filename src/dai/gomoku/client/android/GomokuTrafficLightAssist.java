package dai.gomoku.client.android;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

public class GomokuTrafficLightAssist {

	int i, step, counter = 0;
	Boolean winnerFoundNowExit = false;

	public GomokuTrafficLightAssist(ArrayList<TextView> arrTextViews,
			Context cntx) {

		int dirX[] = { -1, -1, -1, 0, 0, 1, 1, 1 };

		int dirY[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int countX = 0; countX < 15; countX++) {
			for (int countY = 0; countY < 15; countY++) {

				String onSpot = arrTextViews.get((countX * 15) + countY)
						.getText().toString();
				if (onSpot.equals("X") || onSpot.equals("O")) {
					for (i = 0; i < 8; i++) {
						int xc, yc;

						for (step = 1; step <= 4; step++) {
							xc = countX + (step * dirX[i]);
							yc = countY + (step * dirY[i]);

							if (xc > -1 && xc < 15 && yc > -1 && yc < 15) {
								if (!onSpot.equals(arrTextViews
										.get((xc * 15) + yc).getText()
										.toString())) {
									break;
								} else {
									
								}
							} else {
								break;
							}
						}

						

						switch (step) {

						case 2:
							for (int step2 = 1; step2 <= 2; step2++) {
								xc = countX + (step2 * dirX[i]);
								yc = countY + (step2 * dirY[i]);
								if (xc > -1 && xc < 15 && yc > -1 && yc < 15) {
									if (!onSpot.equals(arrTextViews
											.get((xc * 15) + yc).getText()
											.toString())) {
										break;
									} else {
										arrTextViews.get((xc * 15) + yc)
												.setBackgroundColor(
														Color.YELLOW);
									}
								} else {
									break;
								}
							}
							break;

						case 3:
							for (int step3 = 1; step3 <= 3; step3++) {
								xc = countX + (step3 * dirX[i]);
								yc = countY + (step3 * dirY[i]);
								if (xc > -1 && xc < 15 && yc > -1 && yc < 15) {
									if (!onSpot.equals(arrTextViews
											.get((xc * 15) + yc).getText()
											.toString())) {
										break;
									} else {
										arrTextViews.get((xc * 15) + yc)
												.setBackgroundColor(
														Color.RED);
									}
								} else {
									break;
								}
							}
							break;
						default:
							break;
						}
					}
				}

			}

		}
	}
	// this here will loop through the different coordinates on our UI
}
