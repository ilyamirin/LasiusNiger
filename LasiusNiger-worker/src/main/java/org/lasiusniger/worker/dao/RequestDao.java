package org.lasiusniger.worker.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import javax.persistence.EntityManager;
import org.lasiusniger.models.Request;

/**
 *
 * @author ilyamirin
 */
@Singleton
public class RequestDao implements Dao<Request> {

    EntityManager em;

    @Inject
    public RequestDao(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional    
    public void save(Request request) {
        em.persist(request);
    }
}
