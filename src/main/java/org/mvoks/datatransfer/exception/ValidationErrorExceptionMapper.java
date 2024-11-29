package org.mvoks.datatransfer.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationErrorExceptionMapper implements ExceptionMapper<ValidationErrorException> {

    @Override
    public Response toResponse(ValidationErrorException ex) {
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
            .message(ex.getMessage())
            .details(ex.getDetail())
            .build();
        return Response
            .status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(exceptionEntity)
            .build();
    }
}