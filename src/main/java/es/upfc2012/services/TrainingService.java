package es.upfc2012.services;

import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;

public interface TrainingService {

	/**
	 * save a training t for team t.
	 * 
	 * @return the saved training
	 * @throws ServiceException
	 * */
	Training save(Team team, Training training) throws ServiceException;
}
