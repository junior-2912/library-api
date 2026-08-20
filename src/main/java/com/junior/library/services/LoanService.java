package com.junior.library.services;

import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.enums.BookStatus;
import com.junior.library.exceptions.BookIsNotAvailableException;
import com.junior.library.repositories.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Loan not found!"));
    }

    @Transactional
    public Loan save(Loan loan) {

        // Regra de negócio: Livro não pode ser emprestado novamente enquanto o empréstimo estiver ativo.
        Book loanBook = bookService.findById(loan.getBook().getIsbn());
        if (!loanBook.getBookStatus().equals(BookStatus.AVAILABLE)) {
            throw new BookIsNotAvailableException("The book is borrowed");
        }
        loanBook.lend();

        User user = loan.getUser();
        loan.setUser(user);
        return loanRepository.save(loan);
    }

    public List<Loan> saveAll(List<Loan> loans) {
        return loanRepository.saveAll(loans);
    }
}
