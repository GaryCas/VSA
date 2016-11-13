package com.vermellosa.repositories;

import com.googlecode.objectify.cmd.Query;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.services.CalendarService;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 20/08/2016.
 */
public class ChartRepository extends BaseRepository<ChartEntity>{

    @Override
    public Class<ChartEntity> getType() {
        return ChartEntity.class;
    }

    /**
     *
     * @param days
     * @return An array list of different Chart Entities
     *
     * Find a set of chart entities that where created before a specified time
     */
    public Query getQueryWithRange(int days){
            Date date = CalendarService.addDays(days);

            // new Date() indicates that the instance is always "right now" as the application currently does not
            // support queries bounded by two seperate dates. i.e we can not return the results between 3 and 2 weeks ago

            queryBuilder = queryBuilder.withFilter("date <", date);

            date = CalendarService.minusDays(days + 1);

            queryBuilder = queryBuilder.withFilter("date >", date);

            return queryBuilder.build();
    }

    public List<ChartEntity> getListWithDateRange(int days){
        return getQueryWithRange(days).list();
    }

}
