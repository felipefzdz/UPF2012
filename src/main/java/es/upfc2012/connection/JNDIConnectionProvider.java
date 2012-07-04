package es.upfc2012.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 */
public class JNDIConnectionProvider implements ConnectionProvider {

	private static String DATASOURCE_CONTEXT = "java:comp/env/jdbc/ds";

	private Connection _connection;

	public JNDIConnectionProvider() {
		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext
					.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				_connection = datasource.getConnection();
			} else {
				throw new RuntimeException("Failed to lookup datasource.");
			}
		} catch (NamingException ex) {
			throw new RuntimeException("Cannot get connection: " + ex);
		} catch (SQLException ex) {
			throw new RuntimeException("Cannot get connection: " + ex);
		}

	}

	/**
	 * @see es.upfc2012.connection.ConnectionProvider#getConnection()
	 */
	public Connection getConnection() {
		return _connection;
	}

}
