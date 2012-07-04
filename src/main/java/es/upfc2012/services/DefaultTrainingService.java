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

	private static final String UPDATE_TRAININGS = "update trainings set"
			+ " team_login = ? , name = ? , distance = ? ,  training_date = ? where id = ?";

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
			stmt.setLong(index++, training.getDistance());
			stmt.setLong(index++, training.getTrainingDate());

			stmt.execute();
			return training;
		} catch (SQLException e) {
			throw new ServiceException(e);
		}

	}

	public void update(final Team team, final Training t) {
		PreparedStatement stmt;
		try {
			stmt = _provider.getConnection().prepareStatement(UPDATE_TRAININGS);

			int index = 0;
			stmt.setString(index++, team.getName());
			stmt.setString(index++, t.getName());
			stmt.setLong(index++, t.getDistance());
			stmt.setLong(index++, t.getTrainingDate());
			stmt.setString(index++, t.getId());

			stmt.execute();

		} catch (SQLException e) {
		}

	}
}
