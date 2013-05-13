package dai.gomoku.client.android;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class CustomizedListView extends Activity {

	// JSON Node names
	static final String TAG_PLAYER = "players";
	static final String TAG_PLAYER_ID = "playerID";
	static final String TAG_USER_NAME = "userName";
	static final String TAG_FIRST_NAME = "firstName";
	static final String TAG_LAST_NAME = "lastName";

	ListView list;
	LazyAdapter adapter;
	// contacts JSONArray
	JSONArray player = new JSONArray();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Hashmap for ListView
		ArrayList<HashMap<String, String>> playerList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		Intent in = getIntent();
		String msg = in.getStringExtra("json");
		
		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(msg);
		

		try {

			// Getting Array of Contacts
			player = json.getJSONArray(TAG_PLAYER);

			// looping through All Contacts
			for (int i = 0; i < player.length(); i++) {
				JSONObject c = player.getJSONObject(i);

				// Storing each json item in variable
				String id = c.getString(TAG_PLAYER_ID);
				String user_name = c.getString(TAG_USER_NAME);
				String first_name = c.getString(TAG_FIRST_NAME);
				String last_name = c.getString(TAG_LAST_NAME);

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();

				// adding each child node to HashMap key => value
				map.put(TAG_PLAYER_ID, id);
				map.put(TAG_USER_NAME, user_name);
				map.put(TAG_FIRST_NAME, first_name);
				map.put(TAG_LAST_NAME, last_name);

				// adding HashList to ArrayList
				playerList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		list = (ListView) findViewById(R.id.list);

		// Getting adapter by passing xml data ArrayList
		adapter = new LazyAdapter(this, playerList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String plyrID = ((TextView) view.findViewById(R.id.playerID)).getText().toString();
				String uname = ((TextView) view.findViewById(R.id.title)).getText().toString();
				String fname = ((TextView) view.findViewById(R.id.full_name)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(TAG_PLAYER_ID, plyrID);
				in.putExtra(TAG_USER_NAME, uname);
				in.putExtra(TAG_FIRST_NAME, fname);
				startActivity(in);

			}
		});
	}
}