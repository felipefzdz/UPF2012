package es.upfc2012.domain.serializers;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class EntityDigester {
	
	JSONSerializer _serializer = new JSONSerializer();
	
	public <E> E deserialize(String source, Class<E> rootType)
	{
		return new JSONDeserializer<E>().deserialize(source, rootType);
	}
	
	public <E> String serialize(E source)
	{
		return _serializer.deepSerialize(source);
	}
}
