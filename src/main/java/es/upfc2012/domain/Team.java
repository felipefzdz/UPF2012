package es.upfc2012.domain;

import java.util.List;

/**
 * A trail team with four runners.
 * @author guillemmercadal
 *
 */

public class Team {

	private String _id;
	
	private String _name;
	
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
	
}
