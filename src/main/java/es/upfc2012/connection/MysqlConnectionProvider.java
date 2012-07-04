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
public class MysqlConnectionProvider implements ConnectionProvider {

    private final Connection _connection;

    public MysqlConnectionProvider() throws SQLException {
        // org.h2.Driver.load();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        _connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306",
                "upf2012", "upf2012");
    }

    /**
     * @see es.upfc2012.connection.ConnectionProvider#getConnection()
     */
    public Connection getConnection() {
        return _connection;
    }

}
