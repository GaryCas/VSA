package com.vermellosa.parameterised_tests;

import com.vermellosa.entities.BaseEntity;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;
import com.vermellosa.entities.ModelVersion;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;

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
