/**
 * $Id$
 * @author dimitris
 * @date   Jul 4, 2012 1:53:05 PM
 *
 * Copyright (C) 2012 Scytl Secure Electronic Voting SA
 *
 * All rights reserved.
 *
 */
package es.upfc2012.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A pure h2 connection provider.
 */
public class H2ConnectionProvider implements ConnectionProvider {

	private Connection _connection;

	public H2ConnectionProvider()  {
		org.h2.Driver.load();
		try {
			_connection = DriverManager.getConnection("jdbc:h2:file:target/h2.db", "upf2010", "upf2012");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see es.upfc2012.connection.ConnectionProvider#getConnection()
	 */
	public Connection getConnection() {
		return _connection;
	}
}
