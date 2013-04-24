package dai.gomoku.server.requests;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;

public class LoginRequest implements Request {
	private String type = "LOGIN";
	private String username;
	private String password;

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
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

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method is what actually runs the login process, returning an
	 * appropriate response depending on the outcome of the process.
	 */
	@Override
	public Response process() {
		// TODO Auto-generated method stub
		return null;
	}

}
