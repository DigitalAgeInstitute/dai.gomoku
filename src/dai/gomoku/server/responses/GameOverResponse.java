package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.server.Response;

public class GameOverResponse implements Response {
	private final String type = "GAMEOVER";
	private long gameID;
	private String winner;

	public GameOverResponse(long gameID, String winner) {
		this.gameID = gameID;
		this.winner = winner;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameOverResponse [type=" + type + ", gameID=" + gameID
				+ ", winner=" + winner + "]";
	}
	
	

}
