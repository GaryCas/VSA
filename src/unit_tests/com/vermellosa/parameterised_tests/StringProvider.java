package com.vermellosa.parameterised_tests;

import com.vermellosa.entities.ChartEntity;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by rd019985 on 24/11/2016.
 */
public class StringProvider {

    public static final Object[] provideLabels(){

        return new String[]{
               "Happy",
               "Happy",
               "Happy",
               "Happy",
               "Sad",
               "Sad",
               "Sad",
               "Sad",
               "Meh",
               "Meh",
               "Meh"
        };
    }
}
