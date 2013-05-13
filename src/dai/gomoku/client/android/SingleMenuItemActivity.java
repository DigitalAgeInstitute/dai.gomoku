package dai.gomoku.client.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity implements OnClickListener {

	// JSON node keys
	private static final String TAG_ID = "playerID";
	private static final String TAG_USERNAME = "userName";
	private static final String TAG_FIRST_NAME = "firstName";
	Button challenge;
	private ClientThread gomokuTcpClient;
	Handler handler = new Handler();
	String sentJSON, uname;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_list_item);

		// connect to the server
		Intent in = getIntent();

		// set listeners
		challenge = (Button) findViewById(R.id.btnchllg);
		challenge.setOnClickListener(this);

		// Get JSON values from previous intent
		String plyrID = in.getStringExtra(TAG_ID);
		uname = in.getStringExtra(TAG_USERNAME);
		String fname = in.getStringExtra(TAG_FIRST_NAME);

		// Displaying all values on the screen
		TextView lblplyrID = (TextView) findViewById(R.id.email_label);
		TextView lblUName = (TextView) findViewById(R.id.name_label);
		TextView lblFName = (TextView) findViewById(R.id.mobile_label);

		lblplyrID.setText(plyrID);
		lblUName.setText(uname);
		lblFName.setText(fname);
	}

	private String turnObjectToJSON(String challenger, String challengee) {
		//
		Log.e("challenger", challenger);
		Log.e("challengee", challengee);
		sentJSON = String
				.format("{ \"type\":\"CHALLENGE\", \"challengerUsername\":\"%s\", \"challengeeUsername\":\"%s\", \"message\":\"MAKE\" }",
						challenger, challengee);
		return sentJSON;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnchllg:
			checkFieldAndSendMsg();
		}
	}

	private void checkFieldAndSendMsg() {
		// check if there is input
		try {
			handler.post(new Runnable() {
				@Override
				public void run() {
					gomokuTcpClient.sendMessage(turnObjectToJSON("juliusknth",
							"fredmanglis"));
				}
			});

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
