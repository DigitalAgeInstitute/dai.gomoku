package dai.gomoku.client.swing.responses;

import java.util.List;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.ClientController;
import dai.gomoku.game.core.HumanPlayer;

public class SendPlayerResponse implements Response {
	private List<HumanPlayer> players;
	private ClientController controller;

	public SendPlayerResponse(List<HumanPlayer> players, ClientController controller) {
		this.players = players;
		this.controller = controller;
	}

	@Override
	public void process() {
		controller.populateList(players);
	}

}
