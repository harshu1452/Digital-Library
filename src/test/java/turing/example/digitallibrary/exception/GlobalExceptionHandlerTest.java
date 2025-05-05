package turing.example.digitallibrary.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GlobalExceptionHandler class.
 */
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * Initializes the exception handler before each test.
     */
    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    /**
     * Tests handling of ResourceNotFoundException.
     */
    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Book not found");

        ResponseEntity<String> response = globalExceptionHandler.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found", response.getBody());
    }
}
