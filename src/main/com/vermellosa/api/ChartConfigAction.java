package com.vermellosa.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vermellosa.entities.BaseEntity;
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
    @Path("/save/{ent}")
    public void save(@PathParam("ent") String ent){

    }

    @GET
    @Path("/retrieve/{id}")
    public void retrieveConfig(@PathParam("id") String id) {
        long longid = Long.parseLong(id);
        chartConfigRepository.findById(longid);

    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteConfig(@PathParam("id") String id){
       // chartConfigRepository.delete();

    }
}
