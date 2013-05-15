package dai.gomoku.server.responses;

import java.net.Socket;

import dai.gomoku.server.Response;

public class HeartBeatResponse implements Response {

	@Override
	public void respond(Socket socket) {

	}

	@Override
	public String toJSONString() {
		return null;
	}

}
