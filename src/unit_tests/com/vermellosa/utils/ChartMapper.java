package com.vermellosa.utils;

import junitparams.mappers.IdentityMapper;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rd019985 on 11/11/2016.
 */
public class ChartMapper extends IdentityMapper{

    @Override
    public Object[] map(Reader reader){
        Object[] map = super.map(reader);
        List<Object> result = new ArrayList<>();
        String[] args;
        for (Object lineObj : map){
            String line = lineObj.toString();
            int index = line.indexOf(",");
            String arg1 = line.substring(0, index);
            String arg2 = line.substring(index + 1, line.length());
            result.add(new Object[] {arg1, arg2});
        }

        return result.toArray();
    }
}
