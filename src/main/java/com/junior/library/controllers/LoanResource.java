package com.junior.library.controllers;

import com.junior.library.dto.BookRankingDTO;
import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.dto.UserRankingDTO;
import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanResource {

    private final LoanService loanService;

    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> findAll() {
        List<Loan> all = loanService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Loan> findById(@PathVariable Long id) {
        Loan byId = loanService.findById(id);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/late")
    public ResponseEntity<List<Book>> findLateBooks() {
        List<Book> lateBooks = loanService.findLateBooks();
        return ResponseEntity.ok(lateBooks);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserRankingDTO>> findUsersWithTheMostLoans() {
        return ResponseEntity.ok(loanService.findUsersWithTheMostLoans());
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookRankingDTO>> findMoreBorrowedBooks() {
        return ResponseEntity.ok(loanService.findMoreBorrowedBooks());
    }

    @PostMapping
    public ResponseEntity<Loan> save(@Valid @RequestBody LoanRequestDTO loanRequestDTO) {
        Loan save = loanService.save(loanRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();

        return ResponseEntity.created(uri).body(save);
    }

    @PatchMapping("{id}/finish")
    public ResponseEntity<Loan> finishLoan(@PathVariable Long id) {
        Loan loan = loanService.finishLoan(id);
        return ResponseEntity.ok(loan);
    }
}
