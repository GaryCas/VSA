package com.vermellosa.entities;

import com.google.inject.Inject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Hashtable;

/**
 * Created by User on 20/08/2016.
 */
@Entity
public class ChartEntity extends BaseEntity{
    private Hashtable<String, Integer> data;

    public ChartEntity(){
        super();
        this.data = new Hashtable<>();
    }

    public ChartEntity(Long id){
        super(id);
        this.data = new Hashtable<>();
    }

    public ChartEntity(Hashtable data){
        super();
        this.data = data;
    }

    public void incLabelValue(String label){
        data.put(label, getDataValue(label) + 1);
    }

    // Most likely to be used most frequently.
    public void incLabelValueBy(String label, int incValue) throws Exception {
        // data validation. A user should not be able to decrement a value.
        if(incValue > 0) {
            data.put(label, getDataValue(label) + incValue);
        } else {
            throw new IllegalArgumentException("Increment value can not be negative");
        }
    }

    public void setLabelValue(String label, int value){
        if(value > 0) {
            data.put(label, value);
        } else {
            throw new IllegalArgumentException("Increment value can not be negative");
        }
    }

    // data validation. If the the hashmap is accessed directly and the get(label) returns a null,
    // accessing the intValue() method will return a null pointer exception.
    // Accessing data through this method will ensure safety principals and eliminate a possible null pointer exception
    int getDataValue(String label){
        Integer value = getData().get(label);
        if(value == null){
            Hashtable<String, Integer> temp = getData();
            temp.put(label, 0);
            setData(temp);
        }

        return getData().get(label);
    }

    public Hashtable<String, Integer> getData() {
        return data;
    }

    public void setData(Hashtable<String, Integer> data) {
        this.data = data;
    }
}
