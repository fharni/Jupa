package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.BaseEntity;
import de.falkharnisch.web.jupa.producer.qualifier.ContainerManaged;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

abstract class BaseService<T extends BaseEntity> implements Serializable {

    @Inject
    @ContainerManaged
    protected EntityManager em;

    public void persist(T entity) {
        em.persist(entity);
    }

    public T merge(T entity) {
        return em.merge(entity);
    }

    void persistOther(BaseEntity entity) {
        em.persist(entity);
    }

    void mergeOther(BaseEntity entity) {
        em.merge(entity);
    }

    void remove(BaseEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}
