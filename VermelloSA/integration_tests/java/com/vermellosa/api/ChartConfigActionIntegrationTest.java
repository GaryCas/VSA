package com.vermellosa.api;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Inject;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.repositories.ChartConfigRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
public class ChartConfigActionIntegrationTest {


    @Inject
    ChartConfigRepository chartConfigRepository;

    ChartConfig chartConfig;

    LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalDatastoreServiceTestConfig(),
            new LocalMemcacheServiceTestConfig()
    );

    @Before
    public void setUp(){
        helper.setUp();
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
        List<ChartConfig> chartConfigList = chartConfigRepository.findAll();

        // then
        assertEquals("there should be one chart configuration ",1, chartConfigList.size());

    }
}
