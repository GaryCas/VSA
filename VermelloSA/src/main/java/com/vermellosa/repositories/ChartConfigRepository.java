package com.vermellosa.repositories;

import com.vermellosa.entities.ChartConfig;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
public class ChartConfigRepository extends BaseRepository<ChartConfig> {
    @Override
    public Class<ChartConfig> getType() {
        return ChartConfig.class;
    }
}
