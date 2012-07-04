package es.upfc2012.services;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

import es.upfc2012.domain.Training;

public class InMemoryTrainingService implements TrainingService {

	private final HashMap<String, Collection<Training>> _trainingMap = new HashMap<String, Collection<Training>>();

	@Override
	public Training save(final String teamName, final Training training)
			throws ServiceException {

		if (!_trainingMap.keySet().contains(teamName)) {
			_trainingMap.put(teamName, new TreeSet<Training>(
					new TrainingComparator()));
		}
		_trainingMap.get(teamName).add(training);
		training.setId(UUID.randomUUID().toString());
		return training;

	}

	/**
	 * Works as saveOrUpdate. If the team and the training exists remove the
	 * training and insert the new one. If the training doesn't exist insert
	 * without removal. If the team doesn't exist call save method.
	 */
	@Override
	public Training update(final String teamName, final Training training)
			throws ServiceException {
		if (_trainingMap.keySet().contains(teamName)) {
			if (_trainingMap.get(teamName).contains(training)) {
				_trainingMap.get(teamName).remove(training);
			}
			_trainingMap.get(teamName).add(training);
			return training;
		} else {
			return save(teamName, training);
		}
	}

	@Override
	public List<Training> retrieve(final String teamName)
			throws ServiceException {
		if (_trainingMap.containsKey(teamName)) {
			return new ArrayList<Training>(_trainingMap.get(teamName));
		} else {
			return new LinkedList<Training>();
		}
	}

	@Override
	public Training get(final String teamName, final String trainingId)
			throws ServiceException {

		Iterator<Training> iterator = _trainingMap.get(teamName).iterator();

		Training candidate;
		do {
			candidate = iterator.next();
		} while (iterator.hasNext() && !trainingId.equals(candidate.getId()));
		if (trainingId.equals(candidate.getId())) {
			return candidate;
		} else {
			throw new ServiceException("Missing Training ID",
					new InvalidParameterException());
		}
	}

}
