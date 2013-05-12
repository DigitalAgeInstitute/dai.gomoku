package dai.gomoku.server.requests;

import java.sql.SQLException;

import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.server.DBUtils;
import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.responses.RegisterUserResponse;

public class RegisterUserRequest implements Request {
	private String type = "REGISTERUSER";
	private String firstName;
	private String lastName;
	private String email;
	private String contacts;
	private String password;
	private String username;

	public RegisterUserRequest(String firstName, String lastName, String email,
			String contacts, String password, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contacts = contacts;
		this.password = password;
		this.username = username;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	@Override
	public Response process() {
		HumanPlayer player = new HumanPlayer("", username, firstName, lastName);
		player.setEmail(email);
		player.setContacts(contacts);
		try {
			if (DBUtils.registerHumanPlayer(player, password)) {
				return new RegisterUserResponse(RegisterUserResponse.OK,
						"Registration of user successful");
			} else {
				return new RegisterUserResponse(
						RegisterUserResponse.FAIL,
						"username '"
								+ username
								+ "' is already taken. Try a different username.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new RegisterUserResponse(RegisterUserResponse.FAIL,
					"Database Access Error: Try registering again later");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegisterUserRequest [type=" + type + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", contacts="
				+ contacts + ", password=" + password + ", username="
				+ username + "]";
	}

}
