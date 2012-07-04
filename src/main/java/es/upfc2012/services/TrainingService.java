package es.upfc2012.services;

import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;

public interface TrainingService {

	/** save a training t for runner r */
	void save(Team r, Training t);
}
