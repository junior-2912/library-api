package com.junior.library.entities;

import com.junior.library.enums.LoanStatus;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento unidirecional: Loan conhece User, mas User não conhece Loan
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "isbn_book")
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;

    private LoanStatus loanStatus;

    public Loan(Long id, User user, Book book, LocalDate returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.returnDate = returnDate;
        this.loanStatus = LoanStatus.ACTIVE;
    }

    public Loan() {
        this.loanDate = LocalDate.now();
        this.loanStatus = LoanStatus.ACTIVE;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Long getId() {
        return id;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void finish() {
        loanStatus = LoanStatus.FINISHED;
        book.refund();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
