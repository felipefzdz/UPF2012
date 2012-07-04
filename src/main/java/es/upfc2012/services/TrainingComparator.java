package es.upfc2012.services;

import java.util.Comparator;

import es.upfc2012.domain.Training;

public class TrainingComparator implements Comparator<Training> {

	public int compare(final Training o1, final Training o2) {
		return Long.valueOf(o1.getTrainingDate()).compareTo(
				Long.valueOf(o2.getTrainingDate()));
	}

}
