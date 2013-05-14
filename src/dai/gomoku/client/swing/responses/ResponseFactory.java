package dai.gomoku.client.swing.responses;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class ResponseFactory {
	public static Response buildResponseFromWrapper(ResponseWrapper wrapper,
			ClientController controller) {
		System.out.println(wrapper.toString());
		switch (wrapper.getType()) {
		case "LOGIN":
			System.out.println(wrapper.getState());
			return new LoginResponse(wrapper.getState(), controller);
		case "SENDPLAYER":
			return new SendPlayerResponse(wrapper.getPlayers(), controller);
		case "MAKEMOVE":

		default:
			return null;
		}
	}
}
