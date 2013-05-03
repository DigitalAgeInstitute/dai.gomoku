package dai.gomoku.client.android;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GomokuAndroidUI extends Activity {
	ArrayList<TextView> arrTextViews;
	int[][] coordinates = new int[15][15];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		// create an instance of the LinearLayout class called layout

		layout.setOrientation(LinearLayout.VERTICAL);
		// this sets the orientation of the layout(LinearLayout) to vertical

		LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		// this here sets mainParam the layout to fill the whole screen of a
		// device hence refereed to as parent

		layout.setLayoutParams(mainParams);
		// here the layout is set to the params assign to mainParams

		LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f);
		LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
		// here the row and column params are assigned to variable to be used
		// later

		int length = 225;
		int index = 0;
		int no_of_column = 15;
		int num_of_row = length / no_of_column;
		// variable are created and assign values

		arrTextViews = new ArrayList<TextView>(length);


		for (int i = 0; i < length; i++) {
			final TextView textView = new TextView(this);
			// creates an instance of class TextView

			textView.setLayoutParams(colParams);
			// sets the column width

			textView.setId(i);
			textView.setText(" ");

			textView.setGravity(Gravity.CENTER);
			// sets the textView at the centre

			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			// sets the textView size to the value

			/*
			 * textView.setText(count); count++;
			 */

			if ((i % 2) == 0) {

				textView.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.bckgrd));
				// sets the colour of the TextView
			}/*
			 * else { textView.setBackgroundDrawable(getResources().getDrawable(
			 * R.drawable.first_aid_kit)); }
			 */
			textView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TextView tView = (TextView) v;
					tView.setText("X");			
					new FindWhoWins(arrTextViews, GomokuAndroidUI.this);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

}
