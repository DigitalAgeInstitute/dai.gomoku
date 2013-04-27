package dai.gomoku.server;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * This class is to be used for creation of objects that provide database
 * utilities. Among the things it does is have centralised creation of
 * {@link Connection} objects for database access
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class DBUtils {
	private DataSource datasource;
	private Connection connection;

	public DBUtils() throws SQLException {
		initDataSource();
		initConnection();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	private void initDataSource() {
		// TODO: Have these properties in a properties file, then read them from
		// the properties file
		datasource = new MysqlDataSource();
		((MysqlDataSource) datasource).setDatabaseName("gomoku");
		((MysqlDataSource) datasource).setUser("gomoku");
		((MysqlDataSource) datasource).setPassword("gomokupassword");
		((MysqlDataSource) datasource).setPort(3306);
		((MysqlDataSource) datasource).setAutoReconnect(true);
	}

	private void initConnection() throws SQLException {
		connection = datasource.getConnection();
	}

}
