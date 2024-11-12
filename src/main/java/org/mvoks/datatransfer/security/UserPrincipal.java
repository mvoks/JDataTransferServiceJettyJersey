package org.mvoks.datatransfer.security;

import java.security.Principal;
import lombok.Builder;
import lombok.Getter;
import org.mvoks.datatransfer.entity.user.User;

@Builder(builderClassName = "Builder")
@Getter
class UserPrincipal implements Principal {

    private final User user;

    @Override
    public String getName() {
        return user.getUsername();
    }
}