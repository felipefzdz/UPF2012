package es.upfc2012.domain.serializers;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class EntityDigester<E> {
	
	JSONDeserializer<E> _deserializer = new JSONDeserializer<E>();
	JSONSerializer _serializer = new JSONSerializer();
	
	public E deserialize(String source, Class<E> rootType)
	{
		return _deserializer.deserialize(source, rootType);
	}
	
	public String serialize(E source)
	{
		return _serializer.deepSerialize(source);
	}
}
