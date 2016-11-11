package com.vermellosa.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
@Entity
public class ChartConfig  extends BaseEntity{

    public ChartConfig(){
        super();
    }

    public ChartConfig(Long id){
        super(id);
    }

}
