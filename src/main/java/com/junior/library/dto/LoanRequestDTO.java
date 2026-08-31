package com.junior.library.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LoanRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

    @NotNull
    private LocalDate returnDate;

    public LoanRequestDTO() {
    }

    public LoanRequestDTO(Long userId, Long bookId, LocalDate returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.returnDate = returnDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
