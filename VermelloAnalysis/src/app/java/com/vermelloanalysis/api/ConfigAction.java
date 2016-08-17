package com.vermelloanalysis.api;

import com.google.appengine.api.datastore.PreGet;

@Path("/config")
public class ConfigAction {

    @GET
    @Path("/save/{configName}")
    public void save(){

    }

    @GET
    @Path("/retrieve/{configName}")
    public void retrieveConfig() {

    }

    @DELETE
    @Path("/delete/{configName}")
    public void deleteConfig(){

    }
}
