package turing.example.digitallibrary.exception;

/**
 * Custom exception for resource not found cases.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
