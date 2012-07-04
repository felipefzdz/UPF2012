package es.upfc2012.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Test;

public class H2ConnectionProviderTest {
	
	@Test
	public void testGetConnection() throws SQLException{
		H2ConnectionProvider provider = new H2ConnectionProvider();
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			c = provider.getConnection();
			st = c.createStatement();
			rs = st.executeQuery("SELECT 1 FROM DUAL");
			
			int value = rs.getInt(1);
			Assert.assertEquals(1, value);			
		} finally {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(c != null) c.close();
		}
	}

}
