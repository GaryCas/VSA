package com.vermellosa.repositories;

/**
 * Created by rd019985 on 10/11/2016.
 */

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vermellosa.entities.BaseEntity;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.entities.ModelVersion;
import com.vermellosa.parameterised_tests.EntityProvider;
import com.vermellosa.utils.ofyReg;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class BaseRepositoryTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    ChartConfigRepository chartConfigRepository = new ChartConfigRepository();
    ChartRepository chartRepository = new ChartRepository();
    ModelVersionRepository modelVersionRepository = new ModelVersionRepository();


    @Before
    public void setUp(){
        ofyReg.registerObjectifyModels();
        helper.setUp();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }

    @Test
    public void testGetType(){
        assertEquals("Chart config repository is not returning the a chart config as it's type class ",chartConfigRepository.getType(), ChartConfig.class);
        assertEquals("Chart config repository is not returning the a chart entity as it's type class  ",chartRepository.getType(), ChartEntity.class);
        assertEquals("Chart config repository is not returning the a chart entity as it's type class  ",modelVersionRepository.getType(), ModelVersion.class);
    }

    // tests that entities are assigned Ids automatically when they are saved to the datastore.
    @Test
    @Parameters(source= EntityProvider.class, method="provideChartConfigEntities")
    public void testSaveEntity(BaseEntity entity){
        // given

        // when
        assertNull(entity.getId());
        chartConfigRepository.save((ChartConfig) entity);

        // then
        assertNotNull(entity.getId());
    }

    // tests that entities are saved and deleted again
    @Test
    @Parameters(source= EntityProvider.class, method="provideChartConfigEntities")
    public void testSaveAndDeleteEntity(BaseEntity entity){
        // given

        // when
        assertNull(entity.getId());
        chartConfigRepository.save((ChartConfig) entity);

        // then
        assertNotNull(chartConfigRepository.findAll());

        // when 2
        chartConfigRepository.delete(chartConfigRepository.findById(entity.getId()));

        // then 2
        assertNull(chartConfigRepository.findById(entity.getId()));
    }

    // tests that entities are assigned Ids automatically when they are saved to the datastore.
    @Test
    public void testFindById(){
        // given
        ChartConfig entity = new ChartConfig(4L);

        // when
        chartConfigRepository.save((ChartConfig) entity);
        ChartConfig chartConfig = chartConfigRepository.findById(4L);

        // then
        assertNotNull("entity with the id of 4 has not been found ", chartConfig);
    }

    @Test
    @Parameters(source=EntityProvider.class, method="provideEntityList")
    public void testSaveList(ArrayList list){
        // given

        // when 1
        chartConfigRepository.save(list);

        // then
        assertEquals("could not locate the 3 specified entities ",chartConfigRepository.findAll().size(), 3);
    }

    @Test
    @Parameters(source=EntityProvider.class, method="provideEntityList")
    public void testSaveAndDeleteList(ArrayList list){
        // given

        // when 1
        chartConfigRepository.save(list);

        // then 1
        assertEquals("could not locate the 3 specified entities ",chartConfigRepository.findAll().size(), 3);

        // when 2
        list.remove(0);
        assertEquals("remove operation did not work ", list.size(), 2);

        chartConfigRepository.delete(list);
        assertEquals("A single chartConfig is supposed to remain ", chartConfigRepository.findAll().size(), 1);
    }

    @Test
    @Parameters(source=EntityProvider.class, method="provideEntityList")
    public void testFindAllKeys(ArrayList list){
        // given

        // when 1
        chartConfigRepository.save(list);

        // then 1
        assertEquals("could not locate the 3 specified entities ",chartConfigRepository.findAllKeys().list().size(), 3);
    }

    @Test
    public void testDelete(){
        // given
        ChartConfig entity = new ChartConfig(4L);

        // when
        chartConfigRepository.save((ChartConfig) entity);
        ChartConfig chartConfig = chartConfigRepository.findById(4L);

        // then
        assertNotNull("entity with the id of 4 has not been found ", chartConfig);

        // when 2
        chartConfigRepository.delete(4L);
        assertNull(chartConfigRepository.findById(4L));
    }

    @Test
    @Parameters(source=EntityProvider.class, method="provideVariedEntities")
    public void testFilterByType(){

    }
}
