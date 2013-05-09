package dai.gomoku.client.android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Gomoku_login extends Activity implements OnClickListener {

	TextView registerScreen;
	EditText loginEditText, passwordEditText;
	Button signInButton;
	String loginUser, password, sentJSON;
	private ClientThread gomokuTcpClient;
	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.gomoku_login);
		// initalizes variables and views
		initalizeVariables();
		// connect to the server
		//new connectTask().execute("");
		gomokuTcpClient = new ClientThread();
		gomokuTcpClient.start();
		

	}

	private void initalizeVariables() {
		// TODO Auto-generated method stub
		registerScreen = (TextView) findViewById(R.id.link_to_register);
		loginEditText = (EditText) findViewById(R.id.loginNameGomokuET);
		passwordEditText = (EditText) findViewById(R.id.passwordGomokuET);
		signInButton = (Button) findViewById(R.id.signinGomokuBtn);

		// make the below view listeners
		registerScreen.setOnClickListener(this);
		signInButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.link_to_register:
			// Switching to Register screen
			Intent i = new Intent(getApplicationContext(),
					Gomoku_register.class);
			startActivity(i);
			break;
		case R.id.signinGomokuBtn:
			// send sign in data to server
			getValues();
			
			handler.post(new Runnable() {
				@Override
				public void run() {
				gomokuTcpClient.sendMessage(turnObjectToJSON(loginUser, password));
				}
			});
		default:
			break;
		}
	}

	private void getValues() {
		// login name and password that are passed are assigned to strings
		loginUser = loginEditText.getText().toString();
		password = passwordEditText.getText().toString();

	}

	private String turnObjectToJSON(String loginUser2, String password2) {
		//
		Log.e("user", loginUser2);
		Log.e("pass", password2);
		sentJSON = String
				.format("{ \"type\":\"LOGIN\", \"username\":\"%s\", \"password\":\"%s\" }",
						loginUser2, password2);
		return sentJSON;

	}

	/*public class connectTask extends
			AsyncTask<String, String, GomokuAndroidClientTCP> {

		@Override
		protected GomokuAndroidClientTCP doInBackground(String... message) {

			// we create a TCPClient object and

			return null;
		}

	}*/
}
