package es.upfc2012.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upfc2012.connection.MysqlConnectionProvider;
import es.upfc2012.domain.Team;
import es.upfc2012.domain.Training;
import es.upfc2012.domain.requests.JSONEntityType;
import es.upfc2012.domain.serializers.EntityDigester;
import es.upfc2012.services.DefaultTrainingService;
import es.upfc2012.services.ServiceException;
import es.upfc2012.services.TrainingService;

public class TeamController extends HttpServlet {

    private final TrainingService _service = new DefaultTrainingService(
        new MysqlConnectionProvider());

    @Override
    protected void doDelete(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doDelete(req, resp);
    }

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        JSONEntityType type =
            JSONEntityType.valueOf(req.getParameter("type"));
        String teamName = req.getParameter("teamName");
        String trainingId = req.getParameter("training");

        EntityDigester entityDigester = new EntityDigester();

        if (type == JSONEntityType.TRAINING) {
            if (trainingId == null || trainingId.trim().length() <= 0) {
                try {
                    List<Training> trainings = _service.retrieve(teamName);
                    resp.getOutputStream().write(
                        entityDigester.serialize(trainings).getBytes(
                            "UTF-8"));

                } catch (ServiceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    Training training = _service.get(teamName, trainingId);
                    resp.getOutputStream().write(
                        entityDigester.serialize(training).getBytes(
                            "UTF-8"));
                } catch (ServiceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            try {

                List<Training> trainings = _service.retrieve(teamName);

                Team team = new Team();

                team.setName(teamName);

                long distance = 0L;

                for (Training training : trainings) {
                    distance += training.getDistance();
                }

                team.setDistance(distance);

                resp.getOutputStream().write(
                    entityDigester.serialize(team).getBytes("UTF-8"));

            } catch (ServiceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        JSONEntityType type =
            JSONEntityType.valueOf(req.getParameter("type"));
        String teamName = req.getParameter("teamName");
        String jsonSource = req.getParameter("json_descriptor");

        EntityDigester entityDigester = new EntityDigester();

        if (type == JSONEntityType.TRAINING) {
            Training entity =
                entityDigester.deserialize(jsonSource,
                    type.getEntityType());

            try {
                Training result = _service.update(teamName, entity);

                resp.getOutputStream().write(
                    entityDigester.serialize(result).getBytes("UTF-8"));

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        JSONEntityType type =
            JSONEntityType.valueOf(req.getParameter("type"));
        String teamName = req.getParameter("teamName");
        String jsonSource = req.getParameter("json_descriptor");

        EntityDigester entityDigester = new EntityDigester();

        if (type == JSONEntityType.TRAINING) {
            Training entity =
                entityDigester.deserialize(jsonSource,
                    type.getEntityType());

            try {
                Training result = _service.save(teamName, entity);

                resp.getOutputStream().write(
                    entityDigester.serialize(result).getBytes("UTF-8"));

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

}
