package es.upfc2012.services;

import junit.framework.Assert;

import org.junit.Test;

import es.upfc2012.domain.Training;

public class TrainingComparatorTest {

	@Test
	public void testSorting() {
		Training t1 = new Training();
		Training t2 = new Training();
		t1.setTrainingDate(123);
		t2.setTrainingDate(456);
		Assert.assertEquals(1, new TrainingComparator().compare(t1, t2));
	}

}
