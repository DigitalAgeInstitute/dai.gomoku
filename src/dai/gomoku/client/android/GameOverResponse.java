package dai.gomoku.client.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;

import android.os.Handler;

public class GameOverResponse extends Activity {

	static final String TAG_TYPE = "GAMEOVER";
	static final String TAG_WINNER = "winner";
	static final String TAG_GAMEID = "gameID";
	String winner;
	String gameId, sentJSON;
	Handler handler = new Handler();
	GameOVERResponse gameOVERResponse;

	// contacts JSONArray
	JSONArray jsonArr = new JSONArray();

	public void process(String msg) {
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(msg);
		try {

			// Getting Array of Contacts
			jsonArr = json.getJSONArray(TAG_TYPE);

			// looping through All Contacts
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject c = jsonArr.getJSONObject(i);

				// Storing each json item in variable
				winner = c.getString(TAG_WINNER);
				gameId = c.getString(TAG_GAMEID);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		gameOVERResponse.afterMatch();

	}

	@SuppressLint({ "NewApi", "ValidFragment" })
	public class GameOVERResponse extends DialogFragment {
		public void afterMatch() {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("GAME OVER")
					.setMessage(winner + " has won")
					.setPositiveButton("OTHER PLAYERS",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									handler.post(new Runnable() {
										@Override
										public void run() {
											Gomoku_login.gomokuTcpClient.sendMessage(String
													.format("{ \"type\":\"LOGIN\", \"userName\":\"%s\", \"password\":\"%s\",}",
															Gomoku_login.loginUser,
															Gomoku_login.password));
										}
									});
								}
							})
					.setNegativeButton("RETRY",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									try {
										Gomoku_login.gomokuTcpClient.sendMessage(String
												.format("{ \"type\":\"CHALLENGE\", \"challengerUsername\":\"%s\", \"challengeeUsername\":\"%s\", \"message\":\"MAKE}",
														"challenger",
														Gomoku_login.loginUser));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							});
			// Create the AlertDialog object and return it

		}
	}

}
