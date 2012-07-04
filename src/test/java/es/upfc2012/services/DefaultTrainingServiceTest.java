package es.upfc2012.services;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import es.upfc2012.connection.ConnectionProvider;
import es.upfc2012.connection.H2ConnectionProvider;
import es.upfc2012.domain.Training;

public class DefaultTrainingServiceTest {
	
	@Test
	public void testSave() throws SQLException, ServiceException{
		ConnectionProvider provider = new H2ConnectionProvider();
		provider.getConnection().createStatement().execute("RUNSCRIPT FROM 'classpath:db/createDB.sql'");
		
		DefaultTrainingService service = new DefaultTrainingService(provider);
		
		Training training = new Training();
		training.setId("1");
		training.setDistance(23);
		training.setName("My training");
		training.setTrainingDate(System.currentTimeMillis());
		service.save("myTeam", training);
	}

}
