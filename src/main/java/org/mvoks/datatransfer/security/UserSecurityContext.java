package org.mvoks.datatransfer.security;

import java.security.Principal;
import jakarta.ws.rs.core.SecurityContext;
import lombok.Builder;
import org.mvoks.datatransfer.entity.user.Role;

@Builder(builderClassName = "Builder")
class UserSecurityContext implements SecurityContext {

    private final UserPrincipal userPrincipal;
    private final boolean isSecure;

    @Override
    public Principal getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public boolean isUserInRole(String roleName) {
        final Role role = Role.valueOf(roleName);
        return userPrincipal.getUser().getRoles().contains(role);
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return BASIC_AUTH;
    }
}