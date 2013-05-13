package dai.gomoku.client.android;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;
import android.widget.TextView;

public class UpdateUI {

	static final String TAG_TYPE = "MAKEMOVE";
	static final String TAG_STATE = "state";
	static final String TAG_ROW = "row";
	static final String TAG_COL = "col";
	static final String TAG_GAMEID = "gameID";
	String row, col, state;
	String gameId, sentJSON;
	Handler handler = new Handler();
	static boolean ifcanPlay = false;

	// contacts JSONArray
	JSONArray jsonArr = new JSONArray();

	public void update(String msg) {
		updateUIOnThisSide(GomokuAndroidUI.arrTextViews, msg);
	}

	public ArrayList<TextView> updateUIOnThisSide(ArrayList<TextView> XandOs,
			String msg) {

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
				row = c.getString(TAG_ROW);
				col = c.getString(TAG_COL);
				state = c.getString(TAG_STATE);
				gameId = c.getString(TAG_GAMEID);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		int rw, cl;
		rw = Integer.parseInt(row);
		cl = Integer.parseInt(col);

		// now update the UI with the given coordinates
		XandOs.get((rw * 15) + cl).setText("X");
		ifcanPlay = !ifcanPlay;

		return XandOs;

	}

}
