package org.mvoks.datatransfer.dao;

import java.io.Serializable;
import java.util.Optional;

public interface PersistDao<T extends Serializable, ID extends Serializable> {

    T create(T entity);

    Optional<T> findById(ID id);

    T update(T entity);

    void delete(ID id);
}