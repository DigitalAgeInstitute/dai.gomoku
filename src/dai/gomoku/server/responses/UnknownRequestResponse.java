package dai.gomoku.server.responses;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import dai.gomoku.server.Response;

public class UnknownRequestResponse implements Response {
	private String type = "UNKNOWNREQUEST";
	private String requestMade;

	public UnknownRequestResponse(String requestMade) {
		this.requestMade = requestMade;
	}

	@Override
	public String toJSONString() {
		String jsonResponse = String.format(
				"{ \"type\":\"%s\", \"message\":\"%s\"}", this.type,
				this.requestMade);
		return jsonResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnknownRequestResponse [type=" + type + ", requestMade="
				+ requestMade + "]";
	}

	@Override
	public void respond(Socket socket) {
		try {
			PrintWriter writer = new PrintWriter( socket.getOutputStream() );
			writer.write("\n[STARTJSON]\n" + toJSONString() + "\n[ENDJSON]\n");
			writer.flush();
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		}
	}

}
