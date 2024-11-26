package org.mvoks.datatransfer.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.mvoks.datatransfer.dto.user.UserDto;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.mapper.UserMapper;
import org.mvoks.datatransfer.service.UserService;

@Path("/datatransfer/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GET
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getById(@PathParam("id") final Long id) {
        final User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @GET
    @Path("/username/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getById(@PathParam("username") final String username) {
        final User user = userService.getByUsername(username);
        return userMapper.toDto(user);
    }
}