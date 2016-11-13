package com.vermellosa.repositories;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.parameterised_tests.EntityProvider;
import com.vermellosa.utils.OfyRegi;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rd019985 on 11/11/2016.
 */
public class ChartRepositoryTest{

    ChartRepository chartRepository = new ChartRepository();
    LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUpClass(){
        helper.setUp();
        OfyRegi.registerObjectifyModels();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }

    @Test
    public void testDateFilteredQueryWeek() throws Exception {
        // given
        doDatedChartEntities();

        // when
        List<ChartEntity> entities = chartRepository.getListWithDateRange(7);

        // then
        assertEquals("the entities list should contain 15 different chart records, including todays", 15, entities.size());

    }

    @Test
    public void testDateFilteredQuery30Days() throws Exception {
        // given
        doDatedChartEntities();

        // when
        List<ChartEntity> entities = chartRepository.getListWithDateRange(15);

        // then
        assertEquals("the entities list should contain 31 different chart records, including todays",31, entities.size());
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

        chartEntity = new ChartEntity(EntityProvider.getHashdata() , new Date());

        ArrayList<ChartEntity> datedChartEntityList = new ArrayList(Arrays.asList(datedChartEntities));
        datedChartEntityList.add(chartEntity);

        chartRepository.save(datedChartEntityList);
    }

}
