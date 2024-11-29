package org.mvoks.datatransfer.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExpiredJwtExceptionMapper implements ExceptionMapper<ExpiredJwtException> {

    @Override
    public Response toResponse(ExpiredJwtException ex) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message("Access denied.")
            .details(ex.getMessage())
            .build();
        return Response
            .status(Response.Status.FORBIDDEN)
            .type(MediaType.APPLICATION_JSON)
            .entity(exceptionEntity)
            .build();
    }
}