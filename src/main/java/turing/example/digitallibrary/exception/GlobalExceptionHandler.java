package turing.example.digitallibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling application exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where a resource is not found.
     *
     * @param ex The exception thrown.
     * @return Response entity with error message and NOT_FOUND status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
