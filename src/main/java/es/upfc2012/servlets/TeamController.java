package es.upfc2012.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;
import es.upfc2012.domain.requests.JSONEntityType;
import es.upfc2012.domain.serializers.EntityDigester;
import es.upfc2012.services.InMemoryTrainingService;
import es.upfc2012.services.ServiceException;
import es.upfc2012.services.TrainingService;

public class TeamController extends HttpServlet {

	private final TrainingService _service = new InMemoryTrainingService();
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		JSONEntityType type = JSONEntityType.valueOf(req.getParameter("type"));
		String teamName = req.getParameter("teamName");
		String jsonSource = req.getParameter("json_descriptor");
		
		EntityDigester<?> entityDigester = createDigesterForType(type);
		
		Object entity = entityDigester.deserialize(jsonSource, type.getEntityType());

		Team team = new Team();
		team.setName(teamName);
		
		try {
			_service.save(team, (Training)entity);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	} 
	
	protected final EntityDigester<?> createDigesterForType(JSONEntityType type)
	{
		EntityDigester<?> digester;
		
		if(type == JSONEntityType.TRAINING)
		{
			digester = new EntityDigester<Training>();
		}
		else
		{
			digester = new EntityDigester<Team>();
		}
		
		return digester;
	}
}
 