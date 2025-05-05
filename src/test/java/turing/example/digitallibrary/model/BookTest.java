package turing.example.digitallibrary.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Book entity class.
 */
class BookTest {

    /**
     * Tests the all-args constructor and getter methods.
     */
    @Test
    void testBookConstructorAndGetters() {
        Book book = new Book(1L, "Spring Boot Guide", "John Doe", "123-456-789", true);

        assertEquals(1L, book.getId());
        assertEquals("Spring Boot Guide", book.getTitle());
        assertEquals("John Doe", book.getAuthor());
        assertEquals("123-456-789", book.getIsbn());
        assertTrue(book.isAvailable());
    }

    /**
     * Tests the no-args constructor and setter methods.
     */
    @Test
    void testNoArgsConstructorAndSetters() {
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Microservices Architecture");
        book.setAuthor("Jane Doe");
        book.setIsbn("987-654-321");
        book.setAvailable(false);

        assertEquals(2L, book.getId());
        assertEquals("Microservices Architecture", book.getTitle());
        assertEquals("Jane Doe", book.getAuthor());
        assertEquals("987-654-321", book.getIsbn());
        assertFalse(book.isAvailable());
    }

    /**
     * Tests the equals() method to ensure books are compared based on ISBN.
     */
    @Test
    void testEqualsMethod() {
        Book book1 = new Book(1L, "Spring Boot Guide", "John Doe", "123-456-789", true);
        Book book2 = new Book(2L, "Microservices Architecture", "Jane Doe", "123-456-789", false);

        assertEquals(book1, book2);  // Same ISBN → should be equal
    }

    /**
     * Tests the hashCode() method to ensure consistency.
     */
    @Test
    void testHashCodeMethod() {
        Book book1 = new Book(1L, "Spring Boot Guide", "John Doe", "123-456-789", true);
        Book book2 = new Book(2L, "Microservices Architecture", "Jane Doe", "123-456-789", false);

        assertEquals(book1.hashCode(), book2.hashCode());  // Same ISBN → hashCodes must match
    }

    /**
     * Tests the toString() method to verify the string representation.
     */
    @Test
    void testToStringMethod() {
        Book book = new Book(1L, "Spring Boot Guide", "John Doe", "123-456-789", true);
        String expectedString = "Book{id=1, title='Spring Boot Guide', author='John Doe', isbn='123-456-789', available=true}";

        assertEquals(expectedString, book.toString());
    }
}
