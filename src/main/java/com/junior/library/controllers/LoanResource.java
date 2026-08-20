package com.junior.library.controllers;

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

    @PostMapping
    public ResponseEntity<Loan> save(@RequestBody Loan loan) {
        Loan save = loanService.save(loan);
        return ResponseEntity.ok(save);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Loan>> saveAll(@RequestBody List<Loan> loans) {
        List<Loan> loans1 = loanService.saveAll(loans);
        return ResponseEntity.ok(loans1);
    }
}
