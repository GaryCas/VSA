package com.vermelloanalysis.api;

/**
 * Created by User on 17/08/2016.
 */
@Path("/sync")
public class SyncAction {

    @GET
    public void syncNewFeed(){
        // called by cron job

        // calls twitter connector
            // parses with newTweetsParser
        // Ping PAPI
            // parse with outputParser
            // parse with chartableParser
        // Save all three files to Cs using Cloud Storage connector
        
    }
}
