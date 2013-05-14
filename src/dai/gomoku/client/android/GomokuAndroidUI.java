package dai.gomoku.client.android;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GomokuAndroidUI extends Activity {
	static ArrayList<TextView> arrTextViews;
	int[][] coordinates = new int[15][15];
	boolean player1 = true;
	private ClientThread gomokuTcpClient;
	String sentJSON;
	Handler handler = new Handler();
	static LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gomokuTcpClient = new ClientThread(handler);
		gomokuTcpClient.start();

		layout = new LinearLayout(this);

		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(mainParams);
		LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f);
		LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

		int length = 225;
		int index = 0;
		int no_of_column = 15;
		int num_of_row = length / no_of_column;

		arrTextViews = new ArrayList<TextView>(length);

		for (int i = 0; i < length; i++) {
			final TextView textView = new TextView(this);
			textView.setLayoutParams(colParams);
			textView.setId(i);
			textView.setText("");
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

			if ((i % 2) == 0) {
				textView.setBackgroundColor(Color.rgb(166, 172, 169));
			} else {
				textView.setBackgroundColor(Color.rgb(227, 233, 227));
			}

			textView.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					final TextView tView = (TextView) v;
					if (tView.getText().equals("")) {
						if(UpdateUI.ifcanPlay==true){
						handler.post(new Runnable() {
							@Override
							public void run() {
								gomokuTcpClient.sendMessage(String
										.format("{ \"type\":\"MAKEMOVE\", \"username\":\"%s\", \"gameID\":\"%s\", \"row\":\"%s\", \"col\":\"%s\" }",
												Gomoku_login.loginUser, ChallengeResponse.gameId,(tView.getId()/15)+"",(tView.getId()%15)+""));
							}
						});
						}
						tView.setText("O");
						UpdateUI.ifcanPlay = !UpdateUI.ifcanPlay;
					} else {
						Toast.makeText(
								getApplication(),
								((tView.getText()) == "O" ? "player O cant play here"
										: "player X cant play here"),
								Toast.LENGTH_SHORT).show();
					}

					// new FindWhoWins(arrTextViews, GomokuAndroidUI.this);
					v = tView;
				}

			});
			arrTextViews.add(textView);

		}

		for (int i = 0; i < num_of_row; i++) {
			LinearLayout row = new LinearLayout(this);
			row.setLayoutParams(rowParams);
			row.setOrientation(LinearLayout.HORIZONTAL);
			for (int j = 0; j < no_of_column; j++) {
				row.addView(arrTextViews.get(index));
				index++;
			}
			layout.addView(row);
		}
		setContentView(layout);

	}

}
