package com.junior.library.controllers;

import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("late")
    public ResponseEntity<List<Book>> findLateBooks() {
        List<Book> lateBooks = loanService.findLateBooks();
        return ResponseEntity.ok(lateBooks);
    }

    @PostMapping
    public ResponseEntity<Loan> save(@RequestBody LoanRequestDTO loanRequestDTO) {
        Loan save = loanService.save(loanRequestDTO);
        return ResponseEntity.ok(save);
    }

    @PatchMapping("{id}/finish")
    public ResponseEntity<Loan> finishLoan(@PathVariable Long id) {
        Loan loan = loanService.finishLoan(id);
        return ResponseEntity.ok(loan);
    }
}
