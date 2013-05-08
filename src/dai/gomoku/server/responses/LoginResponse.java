package dai.gomoku.server.responses;

import dai.gomoku.server.Response;

public class LoginResponse implements Response {
	public static final String OK = "OK";
	public static final String FAIL = "FAIL";
	
	private String type = "LOGINRESPONSE";
	private String state = "";

	public LoginResponse ( String state ) {
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
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
	public String toXMLString() {
		// TODO: Generate appropriate XML
		return null;
	}

	@Override
	public String toJSONString() {
		return String.format("{ \"type\":\"%s\", \"state\":\"%s\" }", type, state);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginResponse [type=" + type + ", state=" + state + "]";
	}
	
	

}
