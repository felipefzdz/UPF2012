package es.upfc2012.domain.requests;

import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;

public enum JSONEntityType {
	TRAINING(Training.class), TEAM(Team.class);
	
	Class<?> _entityType;
	
	JSONEntityType(Class entityType)
	{
		_entityType = entityType;
	}
	
	public Class getEntityType()
	{
		return _entityType;
	}
}
