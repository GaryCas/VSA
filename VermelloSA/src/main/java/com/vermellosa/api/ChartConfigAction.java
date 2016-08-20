package com.vermellosa.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vermellosa.repositories.ChartConfigRepository;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
@Singleton
@Path("/admin/chart/config")
public class ChartConfigAction {

    @Inject
    ChartConfigRepository chartConfigRepository;


    @GET
    @Path("/save/{configName}")
    public void save(){

    }

    @GET
    @Path("/retrieve/{configName}")
    public void retrieveConfig() {

    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteConfig(@PathParam("id") String id){
       // chartConfigRepository.delete();

    }
}
