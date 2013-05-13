package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.server.Response;

public class RegisterUserResponse implements Response {
	public static final String OK = "OK";
	public static final String FAIL = "FAIL";

	private final String type = "REGISTERUSER";
	private String status;
	private String message;

	public RegisterUserResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public void respond(Socket socket) {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.write("\n[STARTJSON]\n");
			writer.write(toJSONString());
			writer.write("\n[ENDJSON]\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toJSONString() {
		return String.format(
				"{ \"type\":\"%s\", \"status\":\"%s\", \"message\":\"%s\" }",
				type, status, message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegisterUserResponse [type=" + type + ", status=" + status
				+ ", message=" + message + "]";
	}

}
