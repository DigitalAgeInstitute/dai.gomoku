package dai.gomoku.server.responses;

import dai.gomoku.server.Response;

public class UnknownRequestResponse implements Response {
	private String type = "UNKNOWNREQUEST";
	private String requestMade;

	public UnknownRequestResponse(String requestMade) {
		this.requestMade = requestMade;
	}

	@Override
	public String toXMLString() {
		// TODO: Generate appropriate XML String
		return null;
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

}
