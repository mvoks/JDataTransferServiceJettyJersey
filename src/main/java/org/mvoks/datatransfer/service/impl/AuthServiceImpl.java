package org.mvoks.datatransfer.service.impl;

import com.password4j.Password;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.dto.JwtRefresh;
import org.mvoks.datatransfer.dto.JwtRequest;
import org.mvoks.datatransfer.dto.JwtResponse;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.security.JwtService;
import org.mvoks.datatransfer.service.AuthService;
import org.mvoks.datatransfer.service.UserService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public JwtResponse login(final JwtRequest jwtRequest) {
        final User user = userService.getByUsername(jwtRequest.getUsername());
        boolean verified = Password.check(jwtRequest.getPassword(), user.getPassword()).withBcrypt();
        if (verified) {
            return createJwtResponse(user);
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public JwtResponse refresh(final JwtRefresh jwtRefresh) {
        final String token = jwtRefresh.getToken();
        if (!jwtService.validateToken(token)) {
            throw new RuntimeException("Invalid refresh token");
        }
        final Long userId = jwtService.getUserId(token);
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
        return jwtService.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
    }

    private String createRefreshToken(User user) {
        return jwtService.createRefreshToken(user.getId(), user.getUsername());
    }
}