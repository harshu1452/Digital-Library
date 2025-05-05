package turing.example.digitallibrary.utils;

/**
 * Utility class for common library operations.
 */
public class LibraryUtils {

    /**
     * Checks if a given string is null or empty.
     *
     * @param str The string to check.
     * @return True if the string is null or empty, false otherwise.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
