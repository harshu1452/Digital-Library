package turing.example.digitallibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entity class representing a book in the digital library.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Enables the Builder pattern for object creation
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title must be at most 255 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 255, message = "Author must be at most 255 characters")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "ISBN cannot be blank")
    @Size(max = 20, message = "ISBN must be at most 20 characters")
    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private boolean available;

    /**
     * Constructor without ID for creating new Book instances.
     */
    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    /**
     * Returns a string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }

    /**
     * Ensures books are compared correctly based on their ISBN.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn.equals(book.isbn);
    }

    /**
     * Generates a hash code based on the ISBN.
     */
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
