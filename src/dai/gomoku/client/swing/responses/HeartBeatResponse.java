package dai.gomoku.client.swing.responses;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;

public class HeartBeatResponse implements Response {
	private String type = "HEARTBEAT";

	private ClientController controller;

	public HeartBeatResponse(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public void process() {
		String heartJSON = String.format("{ \"type\":\"%s\" }", type);
		controller.sendToServer(heartJSON);
	}

}
