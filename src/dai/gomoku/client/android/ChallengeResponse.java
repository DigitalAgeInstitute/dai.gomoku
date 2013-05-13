package dai.gomoku.client.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class ChallengeResponse extends Activity {

	static final String TAG_CHALLENGERUSERNAME = "challengerUsername";
	static final String TAG_CHALLENGEEUSERNAME = "challengeeUsername";
	static final String TAG_TYPE = "CHALLENGE";
	static final String TAG_MESSAGE = "message";
	static final String TAG_GAMEID = "gameID";
	String challengeeUsername;
	String challengerUsername;
	String message;
	static String gameId;
	String  sentJSON;
	Handler handler = new Handler();
	MatchRequest mRequest;

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
				challengeeUsername = c.getString(TAG_CHALLENGEEUSERNAME);
				challengerUsername = c.getString(TAG_CHALLENGERUSERNAME);
				message = c.getString(TAG_MESSAGE);
				gameId = c.getString(TAG_GAMEID);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mRequest.requestMatch();

	}

	private String turnObjectToJSON(String challenger, String challengee,
			String reply) {
		//
		Log.e("challenger", challenger);
		Log.e("challengee", challengee);
		sentJSON = String
				.format("{ \"type\":\"CHALLENGE\", \"challengerUsername\":\"%s\", \"challengeeUsername\":\"%s\", \"message\":\"$s\" }",
						challengee, challenger, reply);
		return sentJSON;

	}

	@SuppressLint({ "NewApi", "ValidFragment" })
	public class MatchRequest extends DialogFragment {
		public void requestMatch() {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("MATCH REQUEST")
					.setMessage(challengerUsername + " requests for a match")
					.setPositiveButton("PLAY",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent UI = new Intent(
											getApplicationContext(),
											GomokuAndroidUI.class);
									startActivity(UI);
								}
							})
					.setNegativeButton("CANCEL",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									try {
										handler.post(new Runnable() {
											@Override
											public void run() {
												Gomoku_login.gomokuTcpClient
														.sendMessage(turnObjectToJSON(
																Gomoku_login.loginUser,
																challengerUsername,
																"REJECT"));
											}
										});
										
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							});
			// Create the AlertDialog object and return it

		}
	}

}
