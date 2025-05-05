package turing.example.digitallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import turing.example.digitallibrary.model.Book;
import java.util.Optional;

/**
 * Repository interface for managing book persistence.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Finds a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return An Optional containing the book if found.
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Checks if a book with the given ISBN exists.
     *
     * @param isbn The ISBN to check.
     * @return True if a book with the given ISBN exists, otherwise false.
     */
    boolean existsByIsbn(String isbn);
}
