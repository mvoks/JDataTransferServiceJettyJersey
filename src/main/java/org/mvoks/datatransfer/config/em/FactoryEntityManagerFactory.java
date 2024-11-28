package org.mvoks.datatransfer.config.em;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.glassfish.hk2.api.Factory;

public class FactoryEntityManagerFactory implements Factory<EntityManagerFactory> {

    private final EntityManagerFactory entityManagerFactory;

    public FactoryEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
    }

    @Override
    public EntityManagerFactory provide() {
        return entityManagerFactory;
    }

    @Override
    public void dispose(EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }
}