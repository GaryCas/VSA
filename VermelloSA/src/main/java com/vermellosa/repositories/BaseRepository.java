package com.vermellosa.repositories;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.QueryKeys;
import com.sun.istack.logging.Logger;
import com.vermellosa.entities.ChartConfig;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Gary Cassar on 20/08/2016.
 */
public abstract class BaseRepository<T> {

    private static final Logger LOG = Logger.getLogger(BaseRepository.class);

    protected LoadType<T> getBaseLoadQuery(){
        return ofy().load().type(getType());
    }
    
    public T findById(String id) {
        try {
            return getBaseLoadQuery().id(Long.parseLong(id)).now();
        } catch(NumberFormatException e) {
            LOG.log(Level.WARNING, "Error parsing if to long. Returning fake", e);
            ChartConfig chartConfig = new ChartConfig();
            chartConfig.setId(-1L);
            return (T) chartConfig;
        }
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

    public QueryKeys<T> findAllKeys() {
        return getBaseLoadQuery().keys();
    }

    public void delete(Long id) {
//        ofy().delete().entity().now();
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