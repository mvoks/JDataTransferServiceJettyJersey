package org.mvoks.datatransfer.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException ex) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message(ex.getMessage())
            .details(ex.getDetail())
            .build();
        return Response
            .status(Response.Status.UNAUTHORIZED)
            .type(MediaType.APPLICATION_JSON)
            .entity(exceptionEntity)
            .build();
    }
}