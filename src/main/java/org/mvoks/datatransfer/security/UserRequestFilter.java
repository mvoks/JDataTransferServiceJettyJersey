package org.mvoks.datatransfer.security;

import java.net.URI;
import java.util.Set;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.SecurityContext;
import org.glassfish.jersey.server.ContainerRequest;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.exception.AuthenticationException;
import org.mvoks.datatransfer.service.UserService;

@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class UserRequestFilter implements ContainerRequestFilter {

    private static final Set<String> PERMIT_ALL_FILTERED_PATH = Set.of(
        "/datatransfer/v1/auth/"
    );
    @Inject
    @Named("jwtAccessService")
    private JwtService jwtAccessService;
    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        final URI absolutePath = ((ContainerRequest) containerRequestContext).getAbsolutePath();
        if (isAllowPath(absolutePath.getPath())) {
            return;
        }
        try {
            final String authorization = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (authorization != null && authorization.startsWith("Bearer ")) {
                final String token = authorization.substring(7);
                if (jwtAccessService.validateToken(token)) {
                    final SecurityContext securityContext = containerRequestContext.getSecurityContext();
                    final String username = jwtAccessService.getUsername(token);
                    final User user = userService.getByUsername(username);
                    final UserSecurityContext userSecurityContext = UserSecurityContext.builder()
                        .userPrincipal(
                            UserPrincipal.builder()
                                .user(user)
                                .build()
                        )
                        .isSecure(securityContext.isSecure())
                        .build();
                    containerRequestContext.setSecurityContext(userSecurityContext);
                } else {
                    throw new AuthenticationException("Access denied.", "Invalid token.");
                }
            } else {
                throw new AuthenticationException("Access denied.", "Authorization header is invalid.");
            }
        } catch (RuntimeException ex) {
            throw new AuthenticationException("The authorization attempt is invalid.", ex.getMessage());
        }
    }

    private boolean isAllowPath(String path) {
        return PERMIT_ALL_FILTERED_PATH.stream()
            .anyMatch(string -> {
                if (path.length() > string.length()) {
                    final String tempPath = path.substring(0, string.length()).toLowerCase();
                    return tempPath.startsWith(string);
                }
                return false;
            });
    }
}