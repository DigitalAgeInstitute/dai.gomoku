package dai.gomoku.server.requests;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.responses.UnknownRequestResponse;

public class UnknownRequest implements Request {
	private String type;

	public UnknownRequest() {
		type = "UNKNOWN";
	}

	public UnknownRequest(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Response process() {
		return new UnknownRequestResponse(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnknownRequest [type=" + type + "]";
	}

}
