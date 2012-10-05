package org.lasiusniger.worker.dao;

/**
 *
 * @author ilyamirin
 */
public interface Dao<T> {
    
    void save(T obj);
}
