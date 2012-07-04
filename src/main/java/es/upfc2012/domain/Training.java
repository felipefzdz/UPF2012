package es.upfc2012.domain;

public class Training {
	
	private String _id;
	
	private String _name;
	
	private long _distance;
	
	private long _trainingDate;

	public long getTrainingDate() {
		return _trainingDate;
	}

	public void setTrainingDate(long trainingDate) {
		_trainingDate = trainingDate;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getDistance() {
		return _distance;
	}

	public void setDistance(long distance) {
		_distance = distance;
	}
	

}
