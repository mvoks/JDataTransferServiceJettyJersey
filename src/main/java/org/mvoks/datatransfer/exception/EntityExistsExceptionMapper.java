package org.mvoks.datatransfer.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityExistsExceptionMapper implements ExceptionMapper<EntityExistsException> {

    @Override
    public Response toResponse(EntityExistsException ex) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message("Entity already exists.")
            .details(ex.getMessage())
            .build();
        return Response
            .status(Response.Status.FOUND)
            .type(MediaType.APPLICATION_JSON)
            .entity(exceptionEntity)
            .build();
    }
}