package com.wwc.ypt.jpa.persistence;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JPAAccess {
    private EntityManager entityManager;

    public <T> T get(Object id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    public void save(Object entity) {
        entityManager.persist(entity);
    }

    /**
     * @editor caihd
     */
    public int executeUpdateBySql(QueryBuilder queryBuilder) {
        return queryBuilder.buildNative(entityManager, null).executeUpdate();
    }

    public <T> T update(T entity) {
        return entityManager.merge(entity);
    }

    public void remove(Object entity) {
        entityManager.remove(entity);
    }

    public <T> List<T> find(String qlString) {
        return entityManager.createQuery(qlString).getResultList();
    }

    public <T> List<T> find(QueryBuilder queryBuilder) {
        return queryBuilder.build(entityManager).getResultList();
    }

    public int update(QueryBuilder queryBuilder) {
        return queryBuilder.build(entityManager).executeUpdate();
    }

    /**
     * @author caihd
     */
    public <T> List<T> findBySql(QueryBuilder queryBuilder, Class<T> entityClass) {
        if (entityClass == null) {
            throw new IllegalArgumentException("missing entity class.");
        }
        return queryBuilder.buildNative(entityManager, entityClass).getResultList();
    }

    /**
     * @author caihd
     */
    public List findBySql(QueryBuilder queryBuilder) {
        return queryBuilder.buildNative(entityManager, null).getResultList();
    }

    public <T> List<T> find(CriteriaQuery<T> query) {
        return entityManager.createQuery(query).getResultList();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    public <T> T findOne(QueryBuilder queryBuilder) {
        return (T) getOne(queryBuilder.build(entityManager).getResultList());
    }

    /**
     * @author qipeng.yan
     */
    public <T> T findDetachOne(QueryBuilder queryBuilder) {
        return detach((T) findOne(queryBuilder));
    }


    private <T> T getOne(List<T> results) {
        if (results.isEmpty()) {
            return null;
        }
        if (results.size() > 1) {
            throw new NonUniqueResultException("result more than one element, size=" + results.size());
        }
        return results.get(0);
    }

    /**
     * @author caihd
     */
    public <T> T findOneBySql(QueryBuilder queryBuilder, Class<T> entityClass) {
        if (entityClass == null) {
            throw new IllegalArgumentException("missing entity class.");
        }
        return (T) getOne(queryBuilder.buildNative(entityManager, entityClass).getResultList());
    }

    /**
     * @author caihd
     */
    public Object findOneBySql(QueryBuilder queryBuilder) {
        return getOne(queryBuilder.buildNative(entityManager, null).getResultList());
    }

    /**
     * @author qipeng.yan
     */
    public <T> T detach(T entity) {
        if (entity != null) {
            entityManager.detach(entity);
        }
        return entity;
    }

    /**
     * @author qipeng.yan
     */
    public <T> List<T> detach(List<T> entities) {
        if (entities != null) {
            entities.forEach(entity -> entityManager.detach(entity));
        }
        return entities;
    }

    public void flush() {
        entityManager.flush();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
