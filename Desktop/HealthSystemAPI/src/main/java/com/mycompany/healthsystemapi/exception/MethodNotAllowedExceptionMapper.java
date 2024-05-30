package com.mycompany.healthsystemapi.exception;

// Import required libraries
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper class to handle NotAllowedException in JAX-RS resource methods.
 * It converts NotAllowedException into an HTTP response with status code 405 (Method Not Allowed).
 * 
 * @author rachelcooray
 */
@Provider
public class MethodNotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestExceptionMapper.class);

    /**
     * Converts a NotAllowedException into an HTTP response.
     * It returns the HTTP response with status code 405 and an error message.
     */
    @Override
    public Response toResponse(NotAllowedException exception) {
        LOGGER.error("MethodNotAllowedException caught: {}", exception.getMessage(), exception);
        return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                .entity("Method not allowed: " + exception.getMessage())
                .build();
    }
}