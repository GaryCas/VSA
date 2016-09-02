package com.vermellosa.repositories;

import com.vermellosa.entities.ChartEntity;

/**
 * Created by User on 20/08/2016.
 */
public class ChartRepository extends BaseRepository<ChartEntity>{


    @Override
    public Class<ChartEntity> getType() {
        return ChartEntity.class;
    }
}
