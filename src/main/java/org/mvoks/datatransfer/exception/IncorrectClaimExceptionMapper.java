package org.mvoks.datatransfer.exception;

import io.jsonwebtoken.IncorrectClaimException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IncorrectClaimExceptionMapper implements ExceptionMapper<IncorrectClaimException> {

    @Override
    public Response toResponse(IncorrectClaimException ex) {
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