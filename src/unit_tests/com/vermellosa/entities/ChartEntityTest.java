package com.vermellosa.entities;

import com.google.inject.Inject;
import com.vermellosa.parameterised_tests.EntityProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rd019985 on 11/11/2016.
 */
@RunWith(JUnitParamsRunner.class)
public class ChartEntityTest {

    ChartEntity chartEntity = new ChartEntity();

    @Test
    @Parameters(source=EntityProvider.class, method="provideChartEntities")
    public void testInc(ChartEntity chartEntity){
        // given

        // when
        int value = chartEntity.getDataValue("Happy");
        chartEntity.incLabelValue("Happy");

        // then
        assertEquals("Happy label has not been incremented " ,value + 1, chartEntity.getDataValue("Happy"));
        System.out.print(chartEntity.getData());
    }

    @Test
    @Parameters(source=EntityProvider.class, method="provideChartEntities")
    public void testIncBy(ChartEntity chartEntity) throws Exception {
        // given
        int testVal = 5;

        // when
        int value = chartEntity.getDataValue("Happy");
        chartEntity.incLabelValueBy("Happy", testVal);

        // then
        assertEquals("Happy label has not been incremented " ,value + testVal, chartEntity.getDataValue("Happy"));
        System.out.print(chartEntity.getData());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(source=EntityProvider.class, method="provideChartEntities")
    public void testIncByNegative(ChartEntity chartEntity) throws Exception {
        // given
        int testVal = -5;

        // when
        int value = chartEntity.getDataValue("Happy");
        chartEntity.incLabelValueBy("Happy", testVal);

        // then
        // expectException
    }

    @Test (expected = IllegalArgumentException.class)
    @Parameters(source=EntityProvider.class, method="provideChartEntities")
    public void testSetLabelNegVal(ChartEntity chartEntity) throws Exception {
        // given
        int testVal = -5;

        // when
        chartEntity.setLabelValue("Potato", testVal);

        // then
    }

    @Test
    @Parameters(source=EntityProvider.class, method="provideChartEntities")
    public void testSetLabelVal(ChartEntity chartEntity) throws Exception {
        // given
        int testVal = 5;

        // when
        chartEntity.setLabelValue("Potato", testVal);

        // then
        assertEquals("Potato value has not been set." , testVal, chartEntity.getDataValue("Potato"));
        System.out.print(chartEntity.getData());
    }

    @Test
    public void testGetDataValuesForNullValue(){
        // given

        // When
        int value = chartEntity.getDataValue("Happy");

        // Then
        assertEquals("value not intialised to 0", value, 0);
    }
}
