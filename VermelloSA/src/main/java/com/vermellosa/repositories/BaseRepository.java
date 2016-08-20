package com.vermellosa.repositories;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
public abstract class BaseRepository<T> {

    public List<T> findAll() {
        return ofy().load().type(getType()).list();
    }

    public void save(T entity){
        ofy().save().entity(entity).now();
    }

    public abstract Class<T> getType();
}
