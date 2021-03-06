package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.game.core.Player;
import dai.gomoku.server.AvailablePlayersList;
import dai.gomoku.server.Response;

public class LoginResponse implements Response {
	public static final String OK = "OK";
	public static final String FAIL = "FAIL";

	private String type = "LOGIN";
	private String state = "";
	
	private Player player;

	public LoginResponse(String state, Player player ) {
		this.state = state;
		this.player = player;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	@Override
	public String toJSONString() {
		return String.format("{ \"type\":\"%s\", \"state\":\"%s\" }", type,
				state);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginResponse [type=" + type + ", state=" + state + "]";
	}

	@Override
	public synchronized void respond(Socket socket) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			writer.write("\n[STARTJSON]\n" + toJSONString() + "\n[ENDJSON]\n");
			writer.flush();
			if (state == OK) {
				AvailablePlayersList.getInstance().addPlayerToList(player);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
