package com.mycompany.healthsystemapi.exception;

// Import required libraries
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper class to handle BadRequestException in JAX-RS resource methods.
 * It converts BadRequestException into an HTTP response with status code 400 (Bad Request).
 * 
 * @author rachelcooray
 */
@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestExceptionMapper.class);
    
    /**
     * Converts a BadRequestException into an HTTP response.
     * It returns the HTTP response with status code 400 and an error message.
     */
    @Override
    public Response toResponse(BadRequestException exception) {
        LOGGER.error("BadRequestException caught: {}", exception.getMessage(), exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Bad request: " + exception.getMessage())
                .build();
    }
}
