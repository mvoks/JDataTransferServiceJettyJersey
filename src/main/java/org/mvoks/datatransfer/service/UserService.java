package org.mvoks.datatransfer.service;

import org.jvnet.hk2.annotations.Contract;
import org.mvoks.datatransfer.entity.user.User;

@Contract
public interface UserService {

    User create(User user);

    User getById(Long id);

    User getByUsername(String username);

    User updatePassword(User user);

    User update(User user);

    void delete(Long id);
}