package dai.gomoku.client.swing.responses;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class ResponseFactory {
	public static Response buildResponseFromWrapper(ResponseWrapper wrapper,
			ClientController controller) {
		Response response = null;
		System.out.println(wrapper.toString());
		switch (wrapper.getType()) {
		case "LOGIN":
			response =  new LoginResponse(wrapper.getState(), controller);
			break;
		case "SENDPLAYER":
			response = new SendPlayerResponse(wrapper.getPlayers(), controller);
			break;
		case "CHALLENGE":
			response = new ChallengeResponse(controller);
			((ChallengeResponse)response).setGameID( wrapper.getGameID() );
			((ChallengeResponse)response).setChallengeeUsername(wrapper.getChallengeeUsername());
			((ChallengeResponse)response).setChallengerUsername(wrapper.getChallengerUsername());
			((ChallengeResponse)response).setMessage(wrapper.getMessage());
			break;
		case "MAKEMOVE":
			response = new MakeMoveResponse(wrapper.getStatus(),
					wrapper.getGameID(), wrapper.getUsername(),
					wrapper.getRow(), wrapper.getCol(), controller);
			break;
		case "HEARTBEAT":
			response = new HeartBeatResponse(controller);
			break;
		case "GAMEOVER":
			response = new GameOverResponse(controller);
			((GameOverResponse)response).setGameID(wrapper.getGameID());
			((GameOverResponse)response).setWinner(wrapper.getWinner());
			break;
		default:
			response = null;
		}
		return response;
	}
}
