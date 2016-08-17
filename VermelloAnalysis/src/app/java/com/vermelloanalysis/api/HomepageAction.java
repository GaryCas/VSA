package com.vermelloanalysis.api;

import com.google.appengine.api.datastore.PreGet;

/**
 * Created by User on 17/08/2016.
 */
@Path("/homepage")
public class HomepageAction {

    @GET
    @Path("/view/{panel}")
    public void viewPanel(){

    }

}
