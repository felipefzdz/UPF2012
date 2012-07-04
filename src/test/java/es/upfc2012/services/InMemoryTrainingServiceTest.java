package es.upfc2012.services;

import junit.framework.Assert;

import org.junit.Test;

import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;

public class InMemoryTrainingServiceTest {

	@Test
	public void testASavedTrainingGetsAnId() throws ServiceException {

		Team team = new Team();
		team.setName("sample234");
		Training training = new Training();
		training.setName("Session in the forest");

		Training save = new InMemoryTrainingService().save(team, training);
		Assert.assertTrue(!save.getId().equals(null));
	}
	
	@Test
	public void testUpdateTrainingThatPrevioslyExist() throws ServiceException {

		Team team = new Team();
		team.setName("sample234");
		Training training = new Training();
		training.setName("Session in the forest");
		
		TrainingService inMemory = new InMemoryTrainingService();

		Training toUpdate = inMemory.save(team, training);
		toUpdate.setName("Session in the Nintendo");
		Training updated = inMemory.update(team, toUpdate);
		
		Assert.assertTrue(updated.getName().equals("Session in the Nintendo"));
	}
	
	@Test
	public void testUpdateTrainingThatPrevioslyNotExist() throws ServiceException {

		Team team = new Team();
		team.setName("sample234");
		Training training = new Training();
		training.setName("Session in the forest");
		
		TrainingService inMemory = new InMemoryTrainingService();

		Training updated = inMemory.update(team, training);
		
		Assert.assertTrue(updated.getName().equals("Session in the forest"));
	}
}
