package es.upfc2012.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.UUID;

import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;

public class InMemoryTrainingService implements TrainingService {

	private final HashMap<String, Collection<Training>> _trainingMap = new HashMap<String, Collection<Training>>();

	public Training save(final Team team, final Training training)
			throws ServiceException {

		if (!_trainingMap.keySet().contains(team.getName())) {
			_trainingMap.put(team.getName(), new TreeSet<Training>(
					new TrainingComparator()));
		}
		_trainingMap.get(team.getName()).add(training);
		training.setId(UUID.randomUUID().toString());
		return training;

	}
}
