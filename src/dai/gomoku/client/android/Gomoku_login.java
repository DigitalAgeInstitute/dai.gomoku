package dai.gomoku.client.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Gomoku_login extends Activity implements OnClickListener {

	TextView registerScreen;
	EditText loginEditText, passwordEditText;
	Button signInButton;
	CheckBox chckbx;
	public static String password, loginUser;
	String sentJSON;
	static ClientThread gomokuTcpClient;
	Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to login.xml
		setContentView(R.layout.gomoku_login);
		// initalizes variables and views
		initalizeVariables();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				//TextView t = new TextView(Gomoku_login.this);
				//t.setText(gomokuTcpClient.strReceived);
				//Gomoku_login.this.setContentView(t);
				Intent list = new Intent(Gomoku_login.this,CustomizedListView.class);
				list.putExtra("json", gomokuTcpClient.strReceived);
				startActivity(list);
			}
		};

		// connect to the server
		connectToServer();

	}

	private void connectToServer() {
		// new connectTask().execute("");
		gomokuTcpClient = new ClientThread(handler);
		gomokuTcpClient.start();

	}

	private void initalizeVariables() {
		// TODO Auto-generated method stub
		registerScreen = (TextView) findViewById(R.id.link_to_register);
		loginEditText = (EditText) findViewById(R.id.loginNameGomokuET);
		passwordEditText = (EditText) findViewById(R.id.passwordGomokuET);
		signInButton = (Button) findViewById(R.id.signinGomokuBtn);
		chckbx = (CheckBox) findViewById(R.id.gomokucheckBox1);

		// make the below view listeners
		registerScreen.setOnClickListener(this);
		signInButton.setOnClickListener(this);
		chckbx.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.gomokucheckBox1:
			//
			if (chckbx.isChecked()) {
				passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
			} else {
				passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}
			break;
		case R.id.link_to_register:
			// Switching to Register screen
			Intent i = new Intent(getApplicationContext(),
					Gomoku_register.class);
			startActivity(i);
			break;
		case R.id.signinGomokuBtn:
			// send sign in data to server
			getValues();

			// checks if input is correct
			checkFieldAndSendMsg();

		default:
			break;
		}
	}

	private void checkFieldAndSendMsg() {
		// check if there is input
		try {
			// resets error to null
			loginEditText.setError(null);
			passwordEditText.setError(null);

			// checks for valid password.
			if (TextUtils.isEmpty(loginUser)) {
				loginEditText
						.setError(getString(R.string.error_field_required));
			} else if (TextUtils.isEmpty(password)) {
				passwordEditText
						.setError(getString(R.string.error_field_required));
			} else {
				handler.post(new Runnable() {
					@Override
					public void run() {
						gomokuTcpClient.sendMessage(turnObjectToJSON(loginUser,
								password));
					}
				});
			}

		} catch (Exception e) {
			// TODO: handle exception
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
}
