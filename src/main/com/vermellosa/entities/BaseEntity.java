package com.vermellosa.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by rd019985 on 10/11/2016.
 */
@Entity
public class BaseEntity {
    @Id
    private Long id;

    public BaseEntity(){
    }

    public BaseEntity(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
