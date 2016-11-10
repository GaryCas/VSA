package com.vermellosa.repositories;

/**
 * Created by rd019985 on 10/11/2016.
 */

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vermellosa.entities.BaseEntity;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.testUtils;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class BaseRepositoryTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    ChartConfigRepository chartConfigRepository = new ChartConfigRepository();
    ChartRepository chartRepository = new ChartRepository();


    @Before
    public void setUp(){
        testUtils.registerObjectifyModels();
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
    }

    // tests that entities are assigned Ids automatically when they are saved to the datastore.
    @Test
    @Parameters(source= com.vermellosa.parameterised_tests.EntityProvider.class)
    public void testSaveEntity(BaseEntity entity){
        // given

        // when
        assertNull(entity.getId());
        chartConfigRepository.save((ChartConfig) entity);

        // then
        assertNotNull(entity.getId());
    }
}
