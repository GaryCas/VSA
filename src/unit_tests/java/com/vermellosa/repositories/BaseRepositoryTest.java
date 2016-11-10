package com.vermellosa.repositories;

/**
 * Created by rd019985 on 10/11/2016.
 */

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRepositoryTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    ChartConfigRepository chartConfigRepository = new ChartConfigRepository();
    ChartRepository chartRepository = new ChartRepository();


    @Before
    public void setUp(){
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

    @Test
    public void testSaveEntity(){

    }


}
