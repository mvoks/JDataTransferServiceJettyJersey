package org.mvoks.datatransfer.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalStateExceptionMapper implements ExceptionMapper<IllegalStateException> {

    @Override
    public Response toResponse(IllegalStateException ex) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message("Illegal state.")
            .details(ex.getMessage())
            .build();
        return Response
            .status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(exceptionEntity)
            .build();
    }
}