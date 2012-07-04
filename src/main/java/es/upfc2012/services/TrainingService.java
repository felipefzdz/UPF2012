package es.upfc2012.services;

import java.util.List;

import es.upfc2012.domain.Training;

public interface TrainingService {

	/**
	 * save a training t for teamName .
	 * 
	 * @return the saved training
	 * @throws ServiceException
	 * */
	Training save(String teamName, Training training) throws ServiceException;

	/**
	 * update a training t for teamName .
	 * 
	 * @return the updated training
	 * @throws ServiceException
	 * */
	Training update(String teamName, Training training) throws ServiceException;

	/**
	 * retrieve the training list ordered by date for teamName.
	 * 
	 * @return the retrieves training list
	 * @throws ServiceException
	 * */
	List<Training> retrieve(String teamName) throws ServiceException;

	/**
	 * Retrieves a single training given it's ID.
	 * 
	 * @param teamName
	 * @param trainingId
	 * @return the training
	 */
	Training get(final String teamName, final String trainingId) throws ServiceException;

}
