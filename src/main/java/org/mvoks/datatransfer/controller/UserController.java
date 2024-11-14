package org.mvoks.datatransfer.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.mvoks.datatransfer.entity.user.User;
import org.mvoks.datatransfer.service.UserService;

@Path("/datatransfer/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserController {

    private final UserService userService;
    private final SecurityContext securityContext;

    @GET
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") final Long id) {
        return userService.getById(id);
    }

    @GET
    @Path("/username/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("username") final String username) {
        return userService.getByUsername(username);
    }
}