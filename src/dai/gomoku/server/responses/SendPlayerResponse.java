package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import dai.gomoku.game.core.Player;
import dai.gomoku.server.AvailablePlayersList;
import dai.gomoku.server.Response;

public class SendPlayerResponse implements Response {
	private String type = "SENDPLAYER";
	private List<Player> players;
	private String playersString;

	public SendPlayerResponse() {
		this.players = AvailablePlayersList.getInstance()
				.getAvailablePlayersList();
		initPlayersString();
	}

	@Override
	public synchronized void respond(Socket socket) {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.write(toJSONString());
			writer.flush();
			System.out.println(toJSONString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public String toJSONString() {
		return playersString;
	}

	private void initPlayersString() {
		
		// TODO: Implement this correctly
		playersString = String.format(
				"\n[STARTJSON]\n{ \"type\":\"%s\", \"players\":[", type);
		for (Player player : players) {
			System.out.println("PLAYER::: " + player.toJSONString());
			playersString += player.toJSONString()+",";
		}
		playersString = playersString.substring(0, playersString.length() - 1);
		playersString += "] }\n[ENDJSON]\n";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendPlayerResponse [type=" + type + ", players=" + players
				+ "]";
	}

}
