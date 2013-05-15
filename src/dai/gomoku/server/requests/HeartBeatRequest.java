package dai.gomoku.server.requests;

import dai.gomoku.server.HeartBeatChecker;
import dai.gomoku.server.JSONRequestHandler;
import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.responses.HeartBeatResponse;

public class HeartBeatRequest implements Request {
	private JSONRequestHandler handler;
	
	public HeartBeatRequest( JSONRequestHandler handler ) {
		this.handler = handler;
	}

	@Override
	public Response process() {
		HeartBeatChecker.getInstance().resetTimeout(handler);
		return new HeartBeatResponse();
	}

}
