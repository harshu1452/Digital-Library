package turing.example.digitallibrary.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for transferring book data.
 */
@Data
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
}
