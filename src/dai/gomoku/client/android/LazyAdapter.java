package dai.gomoku.client.android;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView playerUName = (TextView)vi.findViewById(R.id.title); // title
        TextView fullNm = (TextView)vi.findViewById(R.id.full_name); // artist name
        TextView plyrID = (TextView)vi.findViewById(R.id.playerID); // duration
        
        HashMap<String, String> player = new HashMap<String, String>();
        player = data.get(position);
        
        // Setting all values in listview
        plyrID.setText("id: " + player.get(CustomizedListView.TAG_PLAYER_ID));
        playerUName.setText(player.get(CustomizedListView.TAG_USER_NAME));
        fullNm.setText(player.get(CustomizedListView.TAG_FIRST_NAME) + " " + player.get(CustomizedListView.TAG_LAST_NAME));
        return vi;
    }
}