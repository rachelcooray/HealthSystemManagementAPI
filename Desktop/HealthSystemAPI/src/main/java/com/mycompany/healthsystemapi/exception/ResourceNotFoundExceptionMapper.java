package com.mycompany.healthsystemapi.exception;

// Import required libraries
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper class to handle ResourceNotFoundExceptions.
 * It implements the ExceptionMapper interface for mapping ResourceNotFoundException to a Response.
 * 
 * @author rachelcooray
 */
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);

    /**
     * Maps a ResourceNotFoundException to a Response object.
     * It returns a Response object with status code 404 (Not Found) and the exception message.
     */
    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        LOGGER.error("ResourceNotFoundException caught: {}", exception.getMessage(), exception);

        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
