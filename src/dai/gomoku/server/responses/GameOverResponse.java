package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.server.Response;

public class GameOverResponse implements Response {
	private final String type = "GAMEOVER";
	private String gameID;
	private String winner;
	private String loser;

	public GameOverResponse(String gameID, String winner, String loser) {
		this.gameID = gameID;
		this.winner = winner;
		this.loser = loser;
	}

	@Override
	public void respond(Socket socket) {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.write("\n[STARTJSON]\n" + toJSONString() + "\n[ENDJSON]\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toJSONString() {
		return String.format(
				"{ \"type\":\"%s\", \"gameID\":\"%s\", \"winner\":\"%s\" }",
				type, gameID, winner);
	}

}
