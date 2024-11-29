package org.mvoks.datatransfer.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException ex) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message("Entity not found.")
            .details(ex.getMessage())
            .build();
        return Response
            .status(Response.Status.NOT_FOUND)
            .type(MediaType.APPLICATION_JSON)
            .entity(exceptionEntity)
            .build();
    }
}