package turing.example.digitallibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import turing.example.digitallibrary.dto.BookDTO;
import turing.example.digitallibrary.model.Book;
import turing.example.digitallibrary.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for LibraryService using JUnit and Mockito.
 */
class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Book book1;
    private Book book2;
    private BookDTO bookDTO;

    /**
     * Setup method to initialize test data before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample books
        book1 = new Book(1L, "Spring Boot Guide", "John Doe", "123-ABC", true);
        book2 = new Book(2L, "Microservices Architecture", "Jane Doe", "456-DEF", true);

        // Sample BookDTO
        bookDTO = new BookDTO();
        bookDTO.setTitle("Spring Boot Guide");
        bookDTO.setAuthor("John Doe");
        bookDTO.setIsbn("123-ABC");
    }

    /**
     * Tests retrieving all books.
     */
    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = libraryService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Spring Boot Guide", result.get(0).getTitle());
        assertEquals("Microservices Architecture", result.get(1).getTitle());

        verify(bookRepository, times(1)).findAll();
    }

    /**
     * Tests adding a new book.
     */
    @Test
    void testAddBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        Book savedBook = libraryService.addBook(bookDTO);

        assertNotNull(savedBook);
        assertEquals("Spring Boot Guide", savedBook.getTitle());
        assertEquals("John Doe", savedBook.getAuthor());
        assertEquals("123-ABC", savedBook.getIsbn());
        assertTrue(savedBook.isAvailable());

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    /**
     * Tests retrieving a book by ID.
     */
    @Test
    void testGetBookById_Found() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        Optional<Book> foundBook = libraryService.getBookById(1L);

        assertTrue(foundBook.isPresent());
        assertEquals("Spring Boot Guide", foundBook.get().getTitle());

        verify(bookRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieving a book by an invalid ID (should return empty).
     */
    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Book> foundBook = libraryService.getBookById(99L);

        assertFalse(foundBook.isPresent());

        verify(bookRepository, times(1)).findById(99L);
    }
}
