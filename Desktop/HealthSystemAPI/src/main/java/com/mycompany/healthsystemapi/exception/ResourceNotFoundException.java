package com.mycompany.healthsystemapi.exception;

/**
 * Exception class to represent resource not found errors.
 * It extends the RuntimeException class.
 * 
 * @author rachelcooray
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
