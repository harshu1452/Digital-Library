package turing.example.digitallibrary.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LibraryUtils.
 */
class LibraryUtilsTest {

    /**
     * Tests when the input string is null.
     */
    @Test
    void testIsNullOrEmpty_NullString() {
        assertTrue(LibraryUtils.isNullOrEmpty(null), "Expected true for null input");
    }

    /**
     * Tests when the input string is empty.
     */
    @Test
    void testIsNullOrEmpty_EmptyString() {
        assertTrue(LibraryUtils.isNullOrEmpty(""), "Expected true for empty string");
    }

    /**
     * Tests when the input string contains only spaces.
     */
    @Test
    void testIsNullOrEmpty_WhitespaceString() {
        assertTrue(LibraryUtils.isNullOrEmpty("   "), "Expected true for whitespace-only string");
    }

    /**
     * Tests when the input string contains non-whitespace characters.
     */
    @Test
    void testIsNullOrEmpty_NonEmptyString() {
        assertFalse(LibraryUtils.isNullOrEmpty("Hello"), "Expected false for non-empty string");
    }
}
