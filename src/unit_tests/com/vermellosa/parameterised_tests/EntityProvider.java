package com.vermellosa.parameterised_tests;

import com.vermellosa.entities.BaseEntity;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

/**
 * Created by rd019985 on 10/11/2016.
 */
@RunWith(JUnitParamsRunner.class)
public class EntityProvider {

    public static final Object[] provideEntities(){
        return new BaseEntity[]{
                new ChartConfig(),
                new ChartConfig()
        };
    }

}
