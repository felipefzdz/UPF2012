package es.upfc2012.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import es.upfc2012.domain.Training;
import es.upfc2012.domain.requests.JSONEntityType;
import es.upfc2012.domain.serializers.EntityDigester;


public class JSONSerializationTest {
	
	@Test
	public void testTrainingSerialization()
	{
		EntityDigester digester = new EntityDigester();
		
		Training training = new Training();
		
		training.setTrainingDate(System.currentTimeMillis());
		training.setName("trainig");
		training.setId("training-id");
		training.setDistance(12L);
		
		System.out.println(digester.serialize(training));
	}

	@Test
	public void testTrainingListSerialization()
	{
		EntityDigester digester = new EntityDigester();
		
		Training training = new Training();
		
		training.setTrainingDate(System.currentTimeMillis());
		training.setName("trainig");
		training.setId("training-id");
		training.setDistance(12L);
		
		List<Training> list = new ArrayList<Training>();
		
		list.add(training);
		
		System.out.println(digester.serialize(list));
	}	
	
	@Test
	public void testTrainingDeserialization()
	{
		String src = "{'distance':12,'id':'training-id','name':'trainig','trainingDate':1341402148067}";
		
		EntityDigester digester = new EntityDigester();
		
		Assert.assertTrue(digester.deserialize(src, JSONEntityType.TRAINING.getEntityType()) instanceof Training);
	}
	
}
