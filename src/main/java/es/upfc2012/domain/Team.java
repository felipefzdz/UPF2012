package es.upfc2012.domain;

/**
 * A trail team with four runners.
 * 
 * @author guillemmercadal
 * 
 */

public class Team {

	private String _name;

	private long _distance;

	public String getName() {
		return _name;
	}

	public void setName(final String name) {
		_name = name;
	}

	public long getDistance() {
		return _distance;
	}

	public void setDistance(final long distance) {
		_distance = distance;
	}

}
