package es.upfc2012.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import es.upfc2012.connection.ConnectionProvider;
import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;

public class DefaultTrainingService implements TrainingService {

	private static final String INSERT_INTO_TRAININGS = "insert into trainings (id, "
			+ "team_login, name, distance, training_date) values (?, ?, ?, ?, ?)";
	private final ConnectionProvider _provider;

	public DefaultTrainingService(final ConnectionProvider provider) {
		_provider = provider;
	}

	public Training save(final Team team, final Training training)
			throws ServiceException {
		PreparedStatement stmt;
		try {
			stmt = _provider.getConnection().prepareStatement(
					INSERT_INTO_TRAININGS);
			training.setId(UUID.randomUUID().toString());

			int index = 0;
			stmt.setString(index++, training.getId());
			stmt.setString(index++, team.getName());
			stmt.setString(index++, training.getName());
			stmt.setString(index++, training.getDistance());
			stmt.setLong(index++, training.getTrainingDate());

			stmt.execute();
			return training;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}

	}
}
