package com.vermellosa;

import com.googlecode.objectify.ObjectifyService;
import com.vermellosa.entities.ChartEntity;

/**
 * Created by User on 20/08/2016.
 */
public class Utils {

    public void registerObjectifyModels(){
        ObjectifyService.register(ChartEntity.class);
    }
}
