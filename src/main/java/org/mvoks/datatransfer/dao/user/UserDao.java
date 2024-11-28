package org.mvoks.datatransfer.dao.user;

import java.util.Optional;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.dao.AbstractPersistDao;
import org.mvoks.datatransfer.entity.user.User;

@Service
public class UserDao extends AbstractPersistDao<User, Long> {

    public Optional<User> findByUsername(final String username) {
        try {
            final TypedQuery<User> typedQuery = getEntityManager()
                .createNamedQuery("user.findByUsername", User.class)
                .setParameter("username", username);
            return Optional.of(typedQuery.getSingleResult());
        } catch (PersistenceException ex) {
            // Ignored
        }
        return Optional.empty();
    }
}