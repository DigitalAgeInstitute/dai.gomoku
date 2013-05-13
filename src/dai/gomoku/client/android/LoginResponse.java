package dai.gomoku.client.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LoginResponse extends Activity {


	static final String TAG_STATE = "state";
	static final String TAG_TYPE = "LOGIN";
	String state;

	// contacts JSONArray
	JSONArray player = new JSONArray();

	public void process(String msg) {
		Log.e("entered LOGIN process json",msg);
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(msg);
		try {

			// Getting Array of Contacts
			player = json.getJSONArray(TAG_TYPE);

			// looping through All Contacts
			for (int i = 0; i < player.length(); i++) {
				JSONObject c = player.getJSONObject(i);

				// Storing each json item in variable
				state = c.getString(TAG_STATE);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (state.equals("OK")) {
			Intent UI = new Intent(this, CustomizedListView.class);
			startActivity(UI);
		} else {
			Toast.makeText(this, "error: try again", Toast.LENGTH_SHORT).show();
		}

	}

}
