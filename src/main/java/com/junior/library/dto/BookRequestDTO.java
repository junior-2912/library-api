package com.junior.library.dto;

public class BookRequestDTO {
    private String isbn;
    private String title;
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
