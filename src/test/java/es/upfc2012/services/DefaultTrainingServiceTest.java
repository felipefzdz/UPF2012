package es.upfc2012.services;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import es.upfc2012.connection.ConnectionProvider;
import es.upfc2012.connection.H2ConnectionProvider;
import es.upfc2012.domain.Training;

public class DefaultTrainingServiceTest {

    @Test
    public void testSaveAndGetTotalDistance() throws SQLException, ServiceException {
        ConnectionProvider provider = new H2ConnectionProvider();
        
        provider.getConnection().createStatement()
        .execute("RUNSCRIPT FROM 'classpath:db/dropDB.sql'");
        provider.getConnection().createStatement()
            .execute("RUNSCRIPT FROM 'classpath:db/createDB.sql'");

        DefaultTrainingService service =
            new DefaultTrainingService(provider);

        Training training = new Training();
        training.setDistance(23);
        training.setName("My training");
        training.setTrainingDate(System.currentTimeMillis());
        service.save("myTeam", training);
        
        training.setDistance(40);
        training.setName("My training 2");
        training.setTrainingDate(System.currentTimeMillis());
        service.save("myTeam", training);

        List<Training> storedTrainings = service.retrieve("myTeam");

        long totalDistance = 0;
        for (Training t : storedTrainings) {
            totalDistance += t.getDistance();
        }

        Assert.assertEquals(63, totalDistance);
    }

}
