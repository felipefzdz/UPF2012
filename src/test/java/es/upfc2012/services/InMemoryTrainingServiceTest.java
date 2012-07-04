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

		Assert.assertTrue(!save.getId().equals(null));

	}
}
