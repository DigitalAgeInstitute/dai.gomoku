package dai.gomoku.client.android;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ResponseFactory  {
	GetResponseType getRspns = new GetResponseType();
	String jsonRecieved;
	LoginResponse lgnRspns = new LoginResponse();
	ChallengeResponse chResponse;
	UpdateUI ui;
	GameOverResponse gameOverResponse;
	Handler handler;
	
	public ResponseFactory(String rcvd) {
		this.jsonRecieved = rcvd;
	}
	
	public void doCalledResponse() {
		Log.e("entered SWITCh json",jsonRecieved);
		switch (ResponseConstants.convertType(GetResponseType.responseType)) {
		case ResponseConstants.LOGIN:
			Log.e("entered SWITCh json",jsonRecieved);
			lgnRspns.process(jsonRecieved);
			break;
		case ResponseConstants.CHALLENGE:
			Log.e("CHALLENGE",ResponseConstants.CHALLENGE+"");
			chResponse.process(jsonRecieved);
			break;
		case ResponseConstants.GAMEOVER:
			Log.e("GAMEOVER",ResponseConstants.GAMEOVER+"");
			gameOverResponse.process(jsonRecieved);
			break;
		case ResponseConstants.MAKEMOVE:
			Log.e("MAKEMOVE",ResponseConstants.MAKEMOVE+"");
			ui.update(jsonRecieved);
			break;
		case ResponseConstants.REGISTERUSER:
			Log.e("REGISTERUSER",ResponseConstants.REGISTERUSER+"");
			break;
		case ResponseConstants.SENDPLAYER:
			Log.e("SENDPLAYER",ResponseConstants.SENDPLAYER+"");
			
			break;
		default:
			break;
		}
		
	}

}
