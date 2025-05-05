package turing.example.digitallibrary.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import turing.example.digitallibrary.dto.BookDTO;
import turing.example.digitallibrary.model.Book;
import turing.example.digitallibrary.service.LibraryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit test for LibraryController using JUnit and Mockito.
 */
class LibraryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private LibraryController libraryController;

    private Book book1;
    private Book book2;
    private BookDTO bookDTO;

    /**
     * Setup method to initialize test data before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(libraryController).build();

        // Sample books
        book1 = new Book(1L, "Spring Boot Guide", "John Doe", "123-456-789", true);
        book2 = new Book(2L, "Microservices Architecture", "Jane Doe", "987-654-321", true);

        // Sample BookDTO
        bookDTO = new BookDTO();
        bookDTO.setTitle("Spring Boot Guide");
        bookDTO.setAuthor("John Doe");
        bookDTO.setIsbn("123-456-789");
    }

    /**
     * Tests fetching all books.
     */
    @Test
    void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(book1, book2);

        when(libraryService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Spring Boot Guide"))
                .andExpect(jsonPath("$[1].title").value("Microservices Architecture"))
                .andDo(print());

        verify(libraryService, times(1)).getAllBooks();
    }

    /**
     * Tests adding a new book.
     */
    @Test
    void testAddBook() throws Exception {
        when(libraryService.addBook(any(BookDTO.class))).thenReturn(book1);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Boot Guide"))
                .andExpect(jsonPath("$.author").value("John Doe"))
                .andExpect(jsonPath("$.isbn").value("123-456-789"))
                .andDo(print());

        verify(libraryService, times(1)).addBook(any(BookDTO.class));
    }

    /**
     * Tests adding a book with a duplicate ISBN (should return 400).
     */
    @Test
    void testAddBook_DuplicateIsbn() throws Exception {
        when(libraryService.addBook(any(BookDTO.class))).thenThrow(new IllegalArgumentException("Book with ISBN 123-456-789 already exists!"));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Book with ISBN 123-456-789 already exists!"))
                .andDo(print());

        verify(libraryService, times(1)).addBook(any(BookDTO.class));
    }

    /**
     * Tests retrieving a book by ID.
     */
    @Test
    void testGetBookById() throws Exception {
        when(libraryService.getBookById(1L)).thenReturn(Optional.of(book1));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Boot Guide"))
                .andDo(print());

        verify(libraryService, times(1)).getBookById(1L);
    }

    /**
     * Tests retrieving a book by an invalid ID (should return 404).
     */
    @Test
    void testGetBookById_NotFound() throws Exception {
        when(libraryService.getBookById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/99"))
                .andExpect(status().isNotFound())  // Now correctly expecting 404
                .andDo(print());

        verify(libraryService, times(1)).getBookById(99L);
    }
}
