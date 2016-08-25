package com.vermellosa.api;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Inject;
import com.vermellosa.GuiceJUnitRunner;
import com.vermellosa.Utils;
import com.vermellosa.di.MainChildModule;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.repositories.ChartConfigRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({MainChildModule.class})
public class ChartConfigActionIntegrationTest {

    // we need inject working here
    @Inject
    ChartConfigRepository chartConfigRepository;

    @Inject
    ChartConfig chartConfig;

    @Inject
    ChartAction chartAction;

    LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalDatastoreServiceTestConfig(),
            new LocalMemcacheServiceTestConfig()
    );

    @Before
    public void setUp(){
        helper.setUp();
        Utils.registerObjectifyModels();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }

    @Test
    public void testSaveAndGetAll() throws Exception {
        // given
        chartConfig = new ChartConfig();
        chartConfig.setId(12345L);
        chartConfig.setName("12345L");
        chartConfigRepository.save(chartConfig);

        // when
        Response response = chartAction.getAll();

        // then
        assertNotNull("there should be one chart configuration ", response.getEntity());
        assertEquals("Should have a status of ", 200, response.getStatus());
        assertEquals("there should be one card config in the datastore ", 1, chartConfigRepository.findAll().size());
    }
}
