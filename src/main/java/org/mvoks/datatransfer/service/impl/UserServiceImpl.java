package org.mvoks.datatransfer.service.impl;

import java.util.Set;
import com.password4j.Password;
import jakarta.annotation.PostConstruct;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.entity.user.Role;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private User user;

    @PostConstruct
    void init() {
        final String password = Password
            .hash("password")
            .withBcrypt()
            .getResult();
        final User tempUser = new User();
        tempUser.setId(1L);
        tempUser.setUsername("username");
        tempUser.setPassword(password);
        tempUser.setRoles(Set.of(Role.ROLE));
        user = tempUser;
    }

    @Override
    public User getById(final Long id) {
        return user;
    }

    @Override
    public User getByUsername(final String username) {
        return user;
    }
}