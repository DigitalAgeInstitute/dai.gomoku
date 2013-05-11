package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.game.core.GomokuGame;
import dai.gomoku.game.core.Player;
import dai.gomoku.server.Response;
import dai.gomoku.server.requests.ChallengeRequest;

public class ChallengeResponse implements Response {
	private String type = "CHALLENGE";
	private String challengerUsername;
	private String challengeeUsername;
	private String message;
	
	

	@Override
	public String toJSONString() {
		return String
				.format("{ \"type\":\"%s\", \"challenger\":\"%s\", \"challengee\":\"%s\", \"message\":\"%s\" }",
						type, challengerUsername, challengeeUsername, message);
	}



	@Override
	public synchronized void respond( Socket socket ) {
		if ( message.equals(ChallengeRequest.MAKE) ) {
			sendTo ( challengeeUsername );
		} else {
			if ( message.equals(ChallengeRequest.ACCEPT) ) {
				Player challenger = ResponseUtil.getPlayer(challengerUsername);
				Player challengee = ResponseUtil.getPlayer(challengeeUsername);
				GomokuGame game  = new GomokuGame(15, challenger, challengee);
				challenger.getPlayerThread().addGame(game.getGameID(), game);
				challengee.getPlayerThread().addGame(game.getGameID(), game);
			}
			sendTo(challengerUsername);
		}
	}
	
	
	
	private void sendTo ( String username ) {
		Socket sock = ResponseUtil.getPlayerSocket(username);
		try {
			PrintWriter writer = new PrintWriter( sock.getOutputStream() );
			writer.write( "\n[STARTJSON]\n" + toJSONString() + "\n[ENDJSON]\n" );
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
