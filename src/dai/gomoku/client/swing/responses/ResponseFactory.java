package dai.gomoku.client.swing.responses;

import dai.gomoku.client.Response;


public class ResponseFactory {
	public static Response buildResponseFromWrapper(ResponseWrapper wrapper) {
		switch ( wrapper.getType() ) {
		case "LOGIN":
			return null;
		default:
			return null;
		}
	}
}
