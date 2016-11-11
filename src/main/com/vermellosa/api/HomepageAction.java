package com.vermellosa.api;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
@Singleton
@Path("/homepage")
@Produces(MediaType.APPLICATION_JSON)
public class HomepageAction {

    @GET
    public void getCharts(){

    }
}
