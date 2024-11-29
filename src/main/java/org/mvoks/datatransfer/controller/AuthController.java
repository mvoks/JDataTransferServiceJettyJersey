package org.mvoks.datatransfer.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.mvoks.datatransfer.dto.auth.JwtRefresh;
import org.mvoks.datatransfer.dto.auth.JwtRequest;
import org.mvoks.datatransfer.dto.auth.JwtResponse;
import org.mvoks.datatransfer.dto.user.UserDto;
import org.mvoks.datatransfer.dto.user.UserRegistrationDto;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.mapper.UserMapper;
import org.mvoks.datatransfer.mapper.UserRegistrationMapper;
import org.mvoks.datatransfer.service.AuthService;
import org.mvoks.datatransfer.service.UserService;
import org.mvoks.datatransfer.validation.ActionCreate;
import org.mvoks.datatransfer.validation.Validated;

@Path("/datatransfer/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuthController {

    private final Validated validated;
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRegistrationMapper userRegistrationMapper;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto register(final UserRegistrationDto userRegistrationDto) {
        validated.validate(userRegistrationDto, ActionCreate.class);
        final User user = userRegistrationMapper.toEntity(userRegistrationDto);
        final User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JwtResponse login(final JwtRequest jwtRequest) {
        validated.validate(jwtRequest);
        return authService.login(jwtRequest);
    }

    @POST
    @Path("/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JwtResponse refresh(final JwtRefresh jwtRefresh) {
        validated.validate(jwtRefresh);
        return authService.refresh(jwtRefresh);
    }
}