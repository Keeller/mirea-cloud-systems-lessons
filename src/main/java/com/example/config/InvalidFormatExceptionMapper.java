package com.example.config;

import com.example.dto.ErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@RegisterForReflection
public class InvalidFormatExceptionMapper implements ExceptionMapper<MismatchedInputException> {

    @Override
    public Response toResponse(MismatchedInputException exception) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity(new ErrorResponse(exception.getMessage())).build();
    }
}
