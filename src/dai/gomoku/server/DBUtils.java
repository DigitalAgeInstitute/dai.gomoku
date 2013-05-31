package dai.gomoku.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import dai.gomoku.game.core.GomokuGame;
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
	private static DataSource dataSource = null;

	/**
	 * @return the connection
	 * @throws SQLException
	 */
	public synchronized static Connection getConnection() throws SQLException {
		createDataSource();
		return dataSource.getConnection();
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
				.format("SELECT * FROM users WHERE username LIKE '%s' AND password LIKE password('%s')",
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
		String userSQL = String
				.format("SELECT user_id, hu.username, firstname, lastname, "
						+ "email, phone FROM users AS u, human_users AS hu WHERE "
						+ "u.username=hu.username AND hu.username LIKE '%s'",
						username);
		ResultSet rs = getResultSet(userSQL);
		Player player = null;
		while (rs.next()) {
			player = new HumanPlayer(rs.getLong("user_id"),
					rs.getString("username"), rs.getString("firstname"),
					rs.getString("lastname"));
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
			createUser(player, password);
			registerPlayer(player);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Saves a new, created game into the database and returns the generated
	 * game_id
	 * 
	 * @param game
	 *            The newly created game
	 * 
	 * @return the generated game_id
	 * 
	 * @throws SQLException
	 */
	public synchronized static long saveNewGame(GomokuGame game)
			throws SQLException {
		long insertID = -1;
		String gameString = String.format(
				"INSERT INTO games (playerO, playerX, status) "
						+ "VALUES ('%s', '%s', 'ONGOING')", game.getPlayer1()
						.getUserID(), game.getPlayer2().getUserID());
		PreparedStatement stmt = getConnection().prepareStatement(gameString,
				Statement.RETURN_GENERATED_KEYS);
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		while (rs.next()) {
			insertID = rs.getLong(1);
		}
		game.setGameID(insertID);
		return insertID;
	}

	/**
	 * Marks the specified game as 'COMPLETE' in the database
	 * 
	 * @param game
	 *            The game to mark as 'COMPLETE'
	 * 
	 * @throws SQLException
	 */
	public static void markGameComplete(GomokuGame game) throws SQLException {
		String sql = String.format(
				"UPDATE games SET status='COMPLETE' where game_id=%d",
				game.getGameID());
		getConnection().prepareStatement(sql).executeUpdate();
	}

	/**
	 * Sets a game as paused when the user explicitly pauses a game.
	 * 
	 * @param game
	 *            The game to mark as 'PAUSED'
	 * 
	 * @throws SQLException
	 */
	public static void markGamePaused(GomokuGame game) throws SQLException {
		String sql = String.format(
				"UPDATE games SET status='PAUSED' where game_id=%d",
				game.getGameID());
		getConnection().prepareStatement(sql).executeUpdate();
	}

	/**
	 * Sets the game as 'FORFEIT' if a user explicitly forfeits the game.
	 * 
	 * @param game
	 *            The game to mark as 'FORFEIT'
	 * 
	 * @throws SQLException
	 */
	public static void markGameForfeit(GomokuGame game) throws SQLException {
		String sql = String.format(
				"UPDATE games SET status='FORFEIT' where game_id=%d",
				game.getGameID());
		getConnection().prepareStatement(sql).executeUpdate();
	}

	/**
	 * This will generally be used for previously paused games when the users
	 * resume the games. It sets the game as 'ONGOING' in the database.
	 * 
	 * @param game
	 *            The game to mark as 'ONGOING'
	 * 
	 * @throws SQLException
	 */
	public static void markGameOngoing(GomokuGame game) throws SQLException {
		String sql = String.format(
				"UPDATE games SET status='ONGOING' where game_id=%d",
				game.getGameID());
		getConnection().prepareStatement(sql).executeUpdate();
	}

	public static long saveMove(long game_id, long player_id, int row, int col) throws SQLException {
		String moveSql = String
				.format("INSERT INTO game_moves(game_id, player_id, row, col) VALUES (%d, %d, %d, %d)",
						game_id, player_id, row, col);
		PreparedStatement stmt = getConnection().prepareStatement(moveSql, Statement.RETURN_GENERATED_KEYS);
		stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		long move_id = -1;
		while ( rs.next() ) {
			move_id = rs.getLong(1);
		}
		return move_id;
	}

	/*
	 * private utility methods
	 */

	private static void registerPlayer(HumanPlayer player) throws SQLException {
		String registerString = String.format(
				"INSERT INTO human_users(username, firstname, lastname, email, phone) "
						+ "VALUES('%s', '%s', '%s', '%s', '%s')",
				player.getUserName(), player.getFirstName(),
				player.getLastName(), player.getEmail(), player.getPhone());
		getConnection().createStatement().execute(registerString);
	}

	private static void createUser(HumanPlayer player, String password)
			throws SQLException {
		String userString = String
				.format("INSERT INTO users(username, password) VALUES('%s', password('%s'))",
						player.getUserName(), password);
		getConnection().createStatement().execute(userString);
	}

	/*
	 * Private utility methods
	 */

	/**
	 * Creates and returns a {@link DataSource} object
	 * 
	 * @return A {@link DataSource} object
	 */
	private static void createDataSource() {
		// TODO: Have these properties in a properties file, then read them from
		// the properties file
		if (dataSource == null) {
			synchronized ("") {
				if (dataSource == null) {
					dataSource = new MysqlDataSource();
					((MysqlDataSource) dataSource).setDatabaseName("gomoku");
					((MysqlDataSource) dataSource).setUser("gomoku");
					((MysqlDataSource) dataSource).setPassword("gomoku");
					((MysqlDataSource) dataSource).setPort(3306);
				}
			}
		}
	}

	private static ResultSet getResultSet(String userSQL) throws SQLException {
		return getConnection().createStatement().executeQuery(userSQL);
	}

	private static boolean usernameExists(String username) throws SQLException {
		String usernameSQL = String.format(
				"SELECT * FROM users WHERE username LIKE '%s'", username);
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
