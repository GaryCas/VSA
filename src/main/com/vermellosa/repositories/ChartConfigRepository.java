package com.vermellosa.repositories;

import com.sun.istack.logging.Logger;
import com.vermellosa.entities.ChartConfig;

import java.util.ArrayList;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
public class ChartConfigRepository extends BaseRepository<ChartConfig> {

    private static final Logger LOG = Logger.getLogger(ChartConfigRepository.class);


    @Override
    public Class<ChartConfig> getType() {
        return ChartConfig.class;
    }


}
