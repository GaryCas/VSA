package com.vermellosa.utils;

import com.googlecode.objectify.ObjectifyService;
import com.vermellosa.entities.ChartConfig;
import com.vermellosa.entities.ChartEntity;

/**
 * Created by User on 20/08/2016.
 */
public class ofyReg {
    public static void registerObjectifyModels(){
        ObjectifyService.register(ChartEntity.class);
        ObjectifyService.register(ChartConfig.class);
    }
}
