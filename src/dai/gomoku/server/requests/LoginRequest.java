package dai.gomoku.server.requests;

import java.sql.SQLException;

import dai.gomoku.game.core.Player;
import dai.gomoku.server.DBUtils;
import dai.gomoku.server.JSONRequestHandler;
import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.responses.LoginResponse;

public class LoginRequest implements Request {
	private String type = "LOGIN";
	private String username;
	private String password;
	private JSONRequestHandler playerThread;

	public LoginRequest(String username, String password,
			JSONRequestHandler playerThread) {
		this.username = username;
		this.password = password;
		this.playerThread = playerThread;
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
	 * @param connectionSocket
	 *            the connectionSocket to set
	 */
	public void setConnectionSocket(JSONRequestHandler playerThread) {
		this.playerThread = playerThread;
	}

	/**
	 * This method is what actually runs the login process, returning an
	 * appropriate response depending on the outcome of the process.
	 */
	@Override
	public Response process() {
		// TODO: Authenticate the user - Does username/password combo exist
		try {
			if (DBUtils.checkUser(username, password)) {
				// TODO: If the user details are ok, create the Player and add
				// to
				// list of available players
				Player player = DBUtils.createPlayer(username);
				player.setPlayerThread(this.playerThread);
				

				// TODO: Return a response indicating success of the
				// authentication
				return new LoginResponse(LoginResponse.OK, player);
			} else {
				// TODO: Return a response indicating failure of the
				// authentication
				return new LoginResponse(LoginResponse.FAIL, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginRequest [type=" + type + ", username=" + username
				+ ", password=" + password + "]";
	}

}
