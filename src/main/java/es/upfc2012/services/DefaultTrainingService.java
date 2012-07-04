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

	public void save(final Team team, final Training t) {
		PreparedStatement stmt;
		try {
			stmt = _provider.getConnection().prepareStatement(
					INSERT_INTO_TRAININGS);
			t.setId(UUID.randomUUID().toString());

			int index = 0;
			stmt.setString(index++, t.getId());
			stmt.setString(index++, team.getName());
			stmt.setString(index++, t.getName());
			stmt.setString(index++, t.getDistance());
			stmt.setLong(index++, t.getTrainingDate());

			stmt.execute();

		} catch (SQLException e) {
		}

	}
}
