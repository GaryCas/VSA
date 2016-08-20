package com.vermellosa.repositories;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
public abstract class BaseRepository<T> {

    protected Query<T> getBaseLoadQuery(){
        return ofy().load().type(getType());
    }

    public Key<T> save(T entity){
        return ofy().save().entity(entity).now();
    }

    public Map<Key<T>, T> save(List<T> entityList){
        return ofy().save().entities(entityList).now();
    }

    public void delete(T entity) {
        ofy().delete().entity(entity).now();
    }

    public void delete(List<T> entities) {
        ofy().delete().entities(entities).now();
    }


    public List<T> findAll() {
        return getBaseLoadQuery().list();
    }

    public abstract Class<T> getType();

    protected class QueryBuilder{
        private Query<T> target;
        boolean targetInitialised = false;

        public QueryBuilder(){
            initTarget();
        }

        private void initTarget() {
            target = getBaseLoadQuery();
            targetInitialised = true;
        }

        public QueryBuilder withFilter(String filter, Object value){
            if(value != null && targetInitialised){
                target.filter(filter, value);
            }
            return this;
        }

        public Query<T> build(){
            return target;
        }

    }
}
