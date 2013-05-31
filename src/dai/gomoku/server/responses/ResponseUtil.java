package dai.gomoku.server.responses;

import java.net.Socket;

import dai.gomoku.game.core.GomokuGame;
import dai.gomoku.game.core.Player;
import dai.gomoku.server.AvailablePlayersList;

public class ResponseUtil {
	
	public static Socket getPlayerSocket(String username) {
		return AvailablePlayersList.getInstance().getPlayerByUsername(username)
				.getPlayerThread().getClientSocket();
	}
	
	public static Player getPlayer ( String username ) {
		return AvailablePlayersList.getInstance().getPlayerByUsername(username);
	}
	
	public static GomokuGame getGameByID ( Player player, long gameID ) {
		return player.getPlayerThread().getGameByID(gameID);
	}
}
