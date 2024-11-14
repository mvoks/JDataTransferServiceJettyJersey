package org.mvoks.datatransfer.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException e) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message(e.getMessage())
            .build();
        if (e.getDetail() != null) {
            return Response
                .status(Response.Status.UNAUTHORIZED)
                .header("WWW-Authenticate", "Basic realm=\"" + e.getDetail() + "\"")
                .type(MediaType.APPLICATION_JSON)
                .entity(exceptionEntity)
                .build();
        } else {
            return Response
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity(exceptionEntity)
                .build();
        }
    }
}