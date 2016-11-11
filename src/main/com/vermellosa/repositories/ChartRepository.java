package com.vermellosa.repositories;

import com.googlecode.objectify.cmd.Query;
import com.vermellosa.entities.ChartEntity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 20/08/2016.
 */
public class ChartRepository extends BaseRepository<ChartEntity>{

    @Override
    public Class<ChartEntity> getType() {
        return ChartEntity.class;
    }

    public ArrayList<ChartEntity> getDataFromAfter(Date date){
            queryBuilder.withFilter("date >=", date);
            Query q = queryBuilder.build();
            return (ArrayList<ChartEntity>) q.list();
    }

}
