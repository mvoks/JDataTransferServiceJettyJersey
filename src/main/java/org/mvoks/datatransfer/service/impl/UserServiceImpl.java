package org.mvoks.datatransfer.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import com.password4j.Password;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.entity.user.Role;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private long id = 0;
    private Map<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException(
                "Password and password confirmation don't match."
            );
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new IllegalStateException("Username already exists");
        }
        user.setId(++id);
        user.setPassword(createPassword(user.getPassword()));
        user.setRoles(Set.of(Role.ROLE));
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User getById(final Long id) {
        return Optional.of(users.get(id))
            .orElseThrow(() -> new IllegalStateException("User doesn't exists"));
    }

    @Override
    public User getByUsername(final String username) {
        return Optional.of(findByUsername(username))
            .orElseThrow(() -> new IllegalStateException("User doesn't exists"));
    }

    @Override
    public User updatePassword(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation don't match.");
        }
        final User userById = Optional.of(users.get(user.getId()))
            .orElseThrow(() -> new IllegalStateException("User doesn't exists"));
        if (!userById.getUsername().equals(user.getUsername())) {
            throw new IllegalStateException("Invalid username.");
        }
        userById.setPassword(createPassword(user.getPassword()));
        users.put(userById.getId(), userById);
        return userById;
    }

    @Override
    public User update(User user) {
        final User userById = Optional.of(users.get(user.getId()))
            .orElseThrow(() -> new IllegalStateException("User doesn't exists"));
        if (!userById.getUsername().equals(user.getUsername())) {
            if (findByUsername(user.getUsername()) != null) {
                throw new IllegalStateException("Username already exists");
            }
        }
        userById.setUsername(user.getUsername());
        userById.setRoles(user.getRoles());
        users.put(userById.getId(), userById);
        return userById;
    }

    @Override
    public void delete(Long id) {
        users.remove(id);
    }

    public User findByUsername(final String username) {
        return users.entrySet().stream()
            .map(Map.Entry::getValue)
            .filter(user -> user.getUsername().equals(username))
            .findAny()
            .orElse(null);
    }

    private String createPassword(String password) {
        return Password
            .hash(password)
            .withBcrypt()
            .getResult();
    }
}