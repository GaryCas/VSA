package com.vermellosa.repositories;

import com.vermellosa.entities.ChartEntity;
import com.vermellosa.parameterised_tests.EntityProvider;
import com.vermellosa.utils.OfyRegi;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rd019985 on 11/11/2016.
 */
public class ChartRepositoryTest {

    ChartRepository chartRepository = new ChartRepository();

    @Before
    public void setUpClass(){
        OfyRegi.registerObjectifyModels();
    }

    @Test
    public void testDateFilteredQuery() throws Exception {
        // given
        doDatedChartEntities();

        // when
        ArrayList<ChartEntity> entities = (ArrayList<ChartEntity>) chartRepository.getListWithDateRange(7);

        // then
        assertEquals(entities.size(), 15);
    }


    public final void doDatedChartEntities(){
        ChartEntity[] datedChartEntities = new ChartEntity[200];
        Calendar calendar = Calendar.getInstance();
        ChartEntity chartEntity;

        for(int i = 0; i < 100; i++){
            calendar.add(Calendar.DATE, 1);
            chartEntity = new ChartEntity(EntityProvider.getHashdata() ,calendar.getTime());
            chartEntity.setId(Long.parseLong(String.valueOf(i)));
            datedChartEntities[i] = new ChartEntity(EntityProvider.getHashdata() ,calendar.getTime());
        }

        calendar.setTime(new Date());

        for(int i = 100; i < 200; i++){
            calendar.add(Calendar.DATE, -1);
            chartEntity = new ChartEntity(EntityProvider.getHashdata() ,calendar.getTime());
            chartEntity.setId(Long.parseLong(String.valueOf(i)));
            datedChartEntities[i] = new ChartEntity(EntityProvider.getHashdata() ,calendar.getTime());
        }

        ArrayList<ChartEntity> datedChartEntityList = new ArrayList(Arrays.asList(datedChartEntities));

        chartRepository.save(datedChartEntityList);
    }

}
