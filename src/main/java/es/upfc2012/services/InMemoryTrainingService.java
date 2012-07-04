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

	/**
	 * Works as saveOrUpdate. If the team and the training 
	 * exists remove the training and insert the new one.
	 * If the training doesn't exist insert without removal. 
	 * If the team doesn't exist call save method.
	 */
	@Override
	public Training update(Team team, Training training)
			throws ServiceException {
		if (_trainingMap.keySet().contains(team.getName())) {
			if (_trainingMap.get(team.getName()).contains(training)){
				_trainingMap.get(team.getName()).remove(training);
			}
			_trainingMap.get(team.getName()).add(training);
			return training;
		}else{
			return save(team, training);
		}
	}
}
