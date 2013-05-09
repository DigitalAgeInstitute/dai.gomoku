package dai.gomoku.client.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gomoku_register extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set View to register.xml
		setContentView(R.layout.gomoku_registration);

		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

		// Listening to Login Screen link
		loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Closing registration screen
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
	}
}
