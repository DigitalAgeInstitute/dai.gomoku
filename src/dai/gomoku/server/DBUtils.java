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

	public static boolean checkUser(String username, String password) throws SQLException {
		String userSQL = String
				.format("SELECT * FROM signup WHERE username LIKE '%s' AND password LIKE password('%s')",
						username, password);
		ResultSet rs = getResultSet( userSQL );
		String user = null;
		while ( rs.next() ) {
			user = rs.getString("username");
		}
		if ( user == null ) {
			return false;
		} else {
			return true;
		}
	}

	private static ResultSet getResultSet(String userSQL) throws SQLException {
		return getConnection().createStatement().executeQuery(userSQL);
	}

	public static Player createPlayer(String username) throws SQLException {
		String userSQL = String.format("SELECT * FROM signup WHERE username LIKE '%s'", username);
		ResultSet rs = getResultSet(userSQL);
		Player player = null;
		while ( rs.next() ) {
			player = new HumanPlayer(rs.getString("userid"), rs.getString("username"), rs.getString("fname"), rs.getString("lname"));
		}
		return player;
	}

	/*
	 * Private utility methods
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

}
