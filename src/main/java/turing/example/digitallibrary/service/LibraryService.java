package turing.example.digitallibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turing.example.digitallibrary.dto.BookDTO;
import turing.example.digitallibrary.model.Book;
import turing.example.digitallibrary.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing book-related operations.
 */
@Service
public class LibraryService {

    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all books in the library.
     *
     * @return A list of books.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Adds a new book to the library after checking for duplicate ISBN.
     *
     * @param bookDTO The book details.
     * @return The saved book entity.
     * @throws IllegalArgumentException if a book with the same ISBN already exists.
     */
    public Book addBook(BookDTO bookDTO) {
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + bookDTO.getIsbn() + " already exists!");
        }

        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getIsbn(), true);
        return bookRepository.save(book);
    }

    /**
     * Finds a book by its ID.
     *
     * @param id The book ID.
     * @return An Optional containing the book if found.
     */
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
