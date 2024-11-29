package org.mvoks.datatransfer.service.impl;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.password4j.Password;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.dto.auth.JwtRefresh;
import org.mvoks.datatransfer.dto.auth.JwtRequest;
import org.mvoks.datatransfer.dto.auth.JwtResponse;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.exception.AuthenticationException;
import org.mvoks.datatransfer.exception.ValidationErrorException;
import org.mvoks.datatransfer.security.JwtService;
import org.mvoks.datatransfer.service.AuthService;
import org.mvoks.datatransfer.service.UserService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    @Named("jwtAccessService")
    private final JwtService jwtAccessService;
    @Named("jwtRefreshService")
    private final JwtService jwtRefreshService;

    @Override
    public JwtResponse login(final JwtRequest jwtRequest) {
        final User user = userService.getByUsername(jwtRequest.getUsername());
        boolean verified = Password.check(jwtRequest.getPassword(), user.getPassword()).withBcrypt();
        if (verified) {
            return createJwtResponse(user);
        }
        throw new AuthenticationException("Invalid username or password");
    }

    @Override
    public JwtResponse refresh(final JwtRefresh jwtRefresh) {
        final String token = jwtRefresh.getToken();
        if (!jwtRefreshService.validateToken(token)) {
            throw new ValidationErrorException("Invalid refresh token");
        }
        final Long userId = jwtRefreshService.getUserId(token);
        final User user = userService.getById(userId);
        return createJwtResponse(user);
    }

    private JwtResponse createJwtResponse(User user) {
        final JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(createAccessToken(user));
        jwtResponse.setRefreshToken(createRefreshToken(user));
        return jwtResponse;
    }

    private String createAccessToken(User user) {
        final Set<String> setRoles = user.getRoles().stream()
            .map(Enum::name)
            .collect(Collectors.toSet());
        final Map<? extends String, ?> parameters = Map.of("roles", setRoles);
        return jwtAccessService.createToken(user.getId(), user.getUsername(), parameters);
    }

    private String createRefreshToken(User user) {
        return jwtRefreshService.createToken(user.getId(), user.getUsername(), null);
    }
}