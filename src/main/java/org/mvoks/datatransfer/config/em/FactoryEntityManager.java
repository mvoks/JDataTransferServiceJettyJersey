package org.mvoks.datatransfer.config.em;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.glassfish.hk2.api.Factory;

public class FactoryEntityManager implements Factory<EntityManager> {

    private final EntityManagerFactory entityManagerFactory;

    @Inject
    public FactoryEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager provide() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void dispose(EntityManager entityManager) {
        entityManagerFactory.close();
    }
}