package dai.gomoku.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

/**
 * This class is to be used for creation of objects that provide database
 * utilities. Among the things it does is have centralised creation of
 * {@link Connection} objects for database access
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class DBUtils {

	/**
	 * @return the connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return createDataSource().getConnection();
	}

	/**
	 * Checks whether the username/password combination that is provided by the
	 * client exists in the database
	 * 
	 * @param username
	 *            The username
	 * @param password
	 *            The password
	 * @return {@code true} if there exists the given username/password
	 *         combination; {@code false} otherwise
	 * @throws SQLException
	 *             Thrown if there is a problem accessing the database
	 */
	public static boolean checkUser(String username, String password)
			throws SQLException {
		String userSQL = String
				.format("SELECT * FROM signup WHERE username LIKE '%s' AND password LIKE password('%s')",
						username, password);
		ResultSet rs = getResultSet(userSQL);
		String user = null;
		while (rs.next()) {
			user = rs.getString("username");
		}
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Should create a {@link Player} object. Right now, it creates a
	 * {@link HumanPlayer} object instead. In future revisions, it should create
	 * whatever type of {@link Player} object is required.
	 * 
	 * @param username
	 *            The username of the player to create
	 * @return A {@link Player} object
	 * @throws SQLException
	 *             Thrown in case of any error
	 */
	public static Player createPlayer(String username) throws SQLException {
		String userSQL = String.format(
				"SELECT * FROM signup WHERE username LIKE '%s'", username);
		ResultSet rs = getResultSet(userSQL);
		Player player = null;
		while (rs.next()) {
			player = new HumanPlayer(rs.getString("userid"),
					rs.getString("username"), rs.getString("fname"),
					rs.getString("lname"));
		}
		return player;
	}

	/**
	 * Used to register a human user
	 * 
	 * @param player
	 *            A {@link HumanPlayer} object
	 * @param password
	 *            The users password
	 * @return {@code true} if registration is successful; {@code false}
	 *         otherwise
	 * @throws SQLException
	 *             Thrown in the case that there is a database access error
	 */
	public static boolean registerHumanPlayer(HumanPlayer player,
			String password) throws SQLException {
		if (!usernameExists(player.getUserName())) {
			String registerString = String
					.format("INSERT INTO signup(fname, lname, email, contacts, "
							+ "password, username) VALUES('%s', '%s', '%s', '%s', password('%s'), '%s')",
							player.getFirstName(), player.getLastName(),
							player.getEmail(), player.getContacts(), password,
							player.getUserName());
			getConnection().createStatement().execute(registerString);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Private utility methods
	 */

	/**
	 * Creates and returns a {@link DataSource} object
	 * 
	 * @return A {@link DataSource} object
	 */
	private static DataSource createDataSource() {
		// TODO: Have these properties in a properties file, then read them from
		// the properties file
		DataSource datasource = new MysqlDataSource();
		((MysqlDataSource) datasource).setDatabaseName("gomoku");
		((MysqlDataSource) datasource).setUser("gomoku");
		((MysqlDataSource) datasource).setPassword("gomoku");
		((MysqlDataSource) datasource).setPort(3306);
		return datasource;
	}

	private static ResultSet getResultSet(String userSQL) throws SQLException {
		return getConnection().createStatement().executeQuery(userSQL);
	}

	private static boolean usernameExists(String username) throws SQLException {
		String usernameSQL = String.format(
				"SELECT * FROM signup WHERE username LIKE '%s'", username);
		ResultSet rs = getResultSet(usernameSQL);
		String user = null;
		while (rs.next()) {
			user = rs.getString("username");
		}
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

}
