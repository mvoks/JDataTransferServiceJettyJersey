package org.mvoks.datatransfer.service;

import org.jvnet.hk2.annotations.Contract;
import org.mvoks.datatransfer.entity.user.User;

@Contract
public interface UserService {

    User getById(Long id);

    User getByUsername(String username);
}