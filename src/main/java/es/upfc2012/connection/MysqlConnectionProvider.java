package es.upfc2012.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A pure h2 connection provider.
 */
public class MysqlConnectionProvider implements ConnectionProvider {

    private Connection _connection = null;

    public MysqlConnectionProvider() {
        // org.h2.Driver.load();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            _connection =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/upf2012", "upf2012",
                    "upf2012");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @see es.upfc2012.connection.ConnectionProvider#getConnection()
     */
    public Connection getConnection() {
        return _connection;
    }

}
