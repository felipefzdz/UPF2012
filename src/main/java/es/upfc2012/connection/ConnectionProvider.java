package es.upfc2012.connection;

import java.sql.Connection;

/**
 * Offers connections to the application.
 * @author guillemmercadal
 *
 */

public interface ConnectionProvider {
	
	/**
	 * Obtains an sql connection.
	 * @return
	 */
	
	Connection getConnection();
	
}
