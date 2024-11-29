package org.mvoks.datatransfer.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public abstract class AbstractPersistDao<T extends Serializable, ID extends Serializable> implements PersistDao<T, ID> {

    @Inject
    private EntityManager entityManager;

    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T create(T entity) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        } catch (PersistenceException ex) {
            transaction.rollback();
        }
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(getObjectClass(), id));
    }

    @Override
    public T update(T entity) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            return entity;
        } catch (PersistenceException ex) {
            transaction.rollback();
        }
        return entity;
    }

    @Override
    public void delete(T id) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(id);
            transaction.commit();
        } catch (PersistenceException ex) {
            transaction.rollback();
        }
    }

    private Class<T> getObjectClass() {
        final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            return (Class<T>) ((ParameterizedType) type).getRawType();
        }
        return (Class<T>) type;
    }
}