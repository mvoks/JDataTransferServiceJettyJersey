package org.mvoks.datatransfer.service.impl;

import java.util.Set;
import com.password4j.Password;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.dao.user.UserDao;
import org.mvoks.datatransfer.entity.user.Role;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.service.UserService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User create(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException(
                "Password and password confirmation don't match."
            );
        }
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new EntityExistsException("User already exists");
        }
        user.setPassword(createPassword(user.getPassword()));
        user.setRoles(Set.of(Role.ROLE));
        return userDao.create(user);
    }

    @Override
    public User getById(final Long id) {
        return userDao.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exists"));
    }

    @Override
    public User getByUsername(final String username) {
        return userDao.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exists"));
    }

    @Override
    public User updatePassword(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation don't match.");
        }
        final User userById = userDao.findById(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exists"));
        if (!userById.getUsername().equals(user.getUsername())) {
            throw new IllegalStateException("Invalid username.");
        }
        user.setPassword(createPassword(user.getPassword()));
        return userDao.update(userById);
    }

    @Override
    public User update(User user) {
        final User userById = userDao.findById(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exists"));
        if (!userById.getUsername().equals(user.getUsername())) {
            if (userDao.findByUsername(user.getUsername()).isPresent()) {
                throw new EntityExistsException("Username already exists");
            }
        }
        userById.setUsername(user.getUsername());
        userById.setRoles(user.getRoles());
        return userDao.update(userById);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    private String createPassword(String password) {
        return Password
            .hash(password)
            .withBcrypt()
            .getResult();
    }
}