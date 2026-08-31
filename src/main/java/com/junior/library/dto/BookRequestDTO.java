package com.junior.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestDTO {
    @NotNull
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    public BookRequestDTO() {
    }

    public BookRequestDTO(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
