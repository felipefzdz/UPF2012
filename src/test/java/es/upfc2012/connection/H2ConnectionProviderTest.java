package es.upfc2012.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Test;

public class H2ConnectionProviderTest {

    @Test
    public void testGetConnection() throws SQLException {
        ConnectionProvider provider = new H2ConnectionProvider();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            c = provider.getConnection();
            st = c.createStatement();
			try {
				st.execute("RUNSCRIPT FROM 'classpath:db/dropDB.sql'");
			}catch(Exception e){

			}
            st.execute("RUNSCRIPT FROM 'classpath:db/createDB.sql'");
            rs = st.executeQuery("SELECT count(*) from trainings");
            if (rs.next()) {
                Assert.assertEquals(0, rs.getInt(1));
            } else {
                Assert.fail("No result!");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

}
