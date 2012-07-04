package es.upfc2012.services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import es.upfc2012.domain.Training;

public class InMemoryTrainingServiceTest {

	private TrainingService _inMemoryTrainingService;
	private String _teamName;
	private Training _training;

	@Before
	public void before() {
		_teamName = "sample234";
		String trainingName = "Session in the forest";
		_training = buildTraining(trainingName, 123);
		_inMemoryTrainingService = new InMemoryTrainingService();
	}

	private Training buildTraining(final String trainingName, final int date) {
		Training training = new Training();
		training.setName(trainingName);
		training.setTrainingDate(date);
		return training;
	}

	@Test
	public void testASavedTrainingGetsAnId() throws ServiceException {
		Training save = _inMemoryTrainingService.save(_teamName, _training);
		Assert.assertTrue(!save.getId().equals(null));
	}

	@Test
	public void testASavedTrainingCanBeRetrieved() throws ServiceException {

		Training save = _inMemoryTrainingService.save(_teamName, _training);
		Training get = _inMemoryTrainingService.get(_teamName, save.getId());
		Assert.assertEquals(save.getId(), get.getId());
	}

	@Test
	public void testUpdateTrainingThatPrevioslyExist() throws ServiceException {
		Training toUpdate = _inMemoryTrainingService.save(_teamName, _training);
		toUpdate.setName("Session in the Nintendo");
		Training updated = _inMemoryTrainingService.update(_teamName, toUpdate);
		Assert.assertTrue(updated.getName().equals("Session in the Nintendo"));
	}

	@Test
	public void testUpdateTrainingThatPrevioslyNotExist()
			throws ServiceException {
		Training updated = _inMemoryTrainingService
				.update(_teamName, _training);
		Assert.assertTrue(updated.getName().equals("Session in the forest"));
	}

	@Test
	public void testRetrieveReturnsAllTrainings() throws Exception {
		_inMemoryTrainingService.save(_teamName, _training);
		_inMemoryTrainingService.save(_teamName, buildTraining("234", 234));
		_inMemoryTrainingService.save(_teamName, buildTraining("456", 456));
		List<Training> retrieve = _inMemoryTrainingService.retrieve(_teamName);
		Assert.assertEquals(3, retrieve.size());
	}

}
