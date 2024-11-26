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
import org.mvoks.datatransfer.service.AuthService;

@Path("/datatransfer/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuthController {

    private final AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JwtResponse login(final JwtRequest jwtRequest) {
        return authService.login(jwtRequest);
    }

    @POST
    @Path("/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JwtResponse refresh(final JwtRefresh jwtRefresh) {
        return authService.refresh(jwtRefresh);
    }
}