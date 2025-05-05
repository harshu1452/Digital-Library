package turing.example.digitallibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import turing.example.digitallibrary.dto.BookDTO;
import turing.example.digitallibrary.model.Book;
import turing.example.digitallibrary.service.LibraryService;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for handling book-related HTTP requests.
 */
@RestController
@RequestMapping("/api/books")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    /**
     * Retrieves all books in the library.
     *
     * @return List of books.
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = libraryService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Adds a new book to the library.
     *
     * @param bookDTO The book details.
     * @return The saved book entity.
     */
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        try {
            Book savedBook = libraryService.addBook(bookDTO);
            return ResponseEntity.ok(savedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // Handles duplicate ISBN scenario
        }
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id The book ID.
     * @return The book entity if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Optional<Book> book = libraryService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());  // Returns 404 if not found
    }
}
