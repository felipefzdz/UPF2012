package es.upfc2012.domain.requests;

public class SimpleJsonEntityRequest {
	
	private JSONEntityType _type;
	private String _jsonDescriptor;
	
	public JSONEntityType getType() {
		return _type;
	}
	public void setType(JSONEntityType type) {
		this._type = type;
	}
	public String getJsonDescriptor() {
		return _jsonDescriptor;
	}
	public void setJsonDescriptor(String jsonDescriptor) {
		this._jsonDescriptor = jsonDescriptor;
	}
	
	
}
