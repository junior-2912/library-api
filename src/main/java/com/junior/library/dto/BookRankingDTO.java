package com.junior.library.dto;

public class BookRankingDTO {
    private String isbn;
    private String title;
    private Long totalLoans;

    public BookRankingDTO(String isbn, String title, Long totalLoans) {
        this.isbn = isbn;
        this.title = title;
        this.totalLoans = totalLoans;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(Long totalLoans) {
        this.totalLoans = totalLoans;
    }
}
