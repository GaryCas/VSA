package com.vermellosa.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.repositories.ChartRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by User on 20/08/2016.
 */
@Singleton
@Path("/chart/action")
@Produces(MediaType.APPLICATION_JSON)
public class ChartAction {

    @Inject
    ChartRepository chartRepository;

    List<ChartEntity> chartEntities;

    private static final Logger LOG = Logger.getLogger(ChartAction.class.getName());


    @GET
    @Path("/testing")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        chartEntities = chartRepository.findAll();

        LOG.log(Level.INFO, "testing ");

        return Response.status(Response.Status.OK).entity(chartEntities).build();
    }


}
