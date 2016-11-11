package com.vermellosa.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Hashtable;

/**
 * Created by User on 20/08/2016.
 */
@Entity
public class ChartEntity extends BaseEntity{
    private Hashtable<String, Integer> data = new Hashtable<>();

    public ChartEntity(){
        super();
    }

    public ChartEntity(Long id){
        super(id);
    }

    public ChartEntity(Hashtable data){
        super();
        this.data = data;
    }

    public void incLabelValue(String label){
        int value = data.get(label).intValue();
        data.put(label, value++);
    }

    public void incLabelValueBy(String label, int incValue){
        int value = data.get(label).intValue();
        data.put(label, value + incValue);
    }

    public void setLabelValue(String label, int incValue){
        data.put(label, incValue);
    }

}
