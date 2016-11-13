package com.vermellosa.parameterised_tests;

import com.vermellosa.entities.BaseEntity;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.entities.ModelVersion;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by rd019985 on 10/11/2016.
 */
@RunWith(JUnitParamsRunner.class)
public class EntityProvider {

    public static final Object[] provideChartConfigEntities(){
        return new BaseEntity[]{
                new ChartConfig(),
                new ChartConfig()
        };
    }

    public static final Object[] provideVariedEntities(){
        return new BaseEntity[]{
                new ChartConfig(1L),
                new ModelVersion(3L)
        };
    }

    public static final Object[] provideChartEntities(){
        Hashtable<String, Integer> data1 = new Hashtable<>();
        data1.put("Happy", 1);
        data1.put("Sad", 2);
        data1.put("Neutral", 3);

        Hashtable<String, Integer> data2 = new Hashtable<>();
        data2.put("Happy", 50);
        data2.put("Sad", 500);

        Hashtable<String, Integer> data3 = new Hashtable<>();

        return new BaseEntity[]{
                new ChartEntity(),
                new ChartEntity(1L),
                new ChartEntity(data1, new Date()),
                new ChartEntity(data2, new Date()),
                new ChartEntity(data3, new Date())
        };
    }

    public static final Object[] provideEntityList(){
        ChartConfig entity1 = new ChartConfig(1L);
        ChartConfig entity2 = new ChartConfig(2L);
        ChartConfig entity3 = new ChartConfig(3L);
        ArrayList<ChartConfig> entityList = new ArrayList<>();

        entityList.add(entity1);
        entityList.add(entity2);
        entityList.add(entity3);

        return new Object[]{
                entityList
        };
    }



}
