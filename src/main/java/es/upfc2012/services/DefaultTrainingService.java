package es.upfc2012.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import es.upfc2012.connection.ConnectionProvider;
import es.upfc2012.domain.Training;

public class DefaultTrainingService implements TrainingService {

	private static final String INSERT_INTO_TRAININGS = "insert into trainings (id, "
			+ "team_login, name, distance, training_date) values (?, ?, ?, ?, ?)";

	private static final String UPDATE_TRAININGS = "update trainings set"
			+ " team_login = ? , name = ? , distance = ? ,  training_date = ? where id = ?";

	private static final String RETRIEVE_TRAININGS = "select name from trainings where team_login = ? order by desc training_date";
	
	private final ConnectionProvider _provider;

	public DefaultTrainingService(final ConnectionProvider provider) {
		_provider = provider;
	}

	public Training save(final String teamName, final Training training)
			throws ServiceException {
		PreparedStatement stmt;
		try {
			stmt = _provider.getConnection().prepareStatement(
					INSERT_INTO_TRAININGS);
			training.setId(UUID.randomUUID().toString());

			int index = 0;
			stmt.setString(index++, training.getId());
			stmt.setString(index++, teamName);
			stmt.setString(index++, training.getName());
			stmt.setLong(index++, training.getDistance());
			stmt.setLong(index++, training.getTrainingDate());

			stmt.execute();
			return training;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}

	}

	public Training update(final String teamName, final Training training) throws ServiceException {
		PreparedStatement stmt;
		try {
			stmt = _provider.getConnection().prepareStatement(UPDATE_TRAININGS);

			int index = 0;
			stmt.setString(index++, teamName);
			stmt.setString(index++, training.getName());
			stmt.setLong(index++, training.getDistance());
			stmt.setLong(index++, training.getTrainingDate());
			stmt.setString(index++, training.getId());

			stmt.execute();

			return training;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Training> retrieve(String teamName) throws ServiceException {
		PreparedStatement stmt;
		List<Training> trainings = new ArrayList<Training>();
		try {
			stmt = _provider.getConnection().prepareStatement(RETRIEVE_TRAININGS);

			int index = 0;
			stmt.setString(index++, teamName);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()){
				Training training = new Training();
				training.setName(result.getString(1));
				trainings.add(training);
			}
			return trainings;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
}
