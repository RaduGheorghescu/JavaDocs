package ro.teamnet.zth.api.em;

import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Gheorghescu on 7/13/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id);
    Long getNextIdVal(String tableName, String columnIdName);
    <T> Object insert(T entity);
    <T> List<T> findAll(Class<T> entityClass);
    <T> T update(T entity);
    void delete(Object entity);
    <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params);
}
