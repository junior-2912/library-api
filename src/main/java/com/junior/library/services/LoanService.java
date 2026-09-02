package com.junior.library.services;

import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.enums.BookStatus;
import com.junior.library.enums.LoanStatus;
import com.junior.library.exceptions.*;
import com.junior.library.repositories.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final BookService bookService;

    private final UserService userService;

    public LoanService(LoanRepository loanRepository, BookService bookService, UserService userService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found!"));
    }

    @Transactional
    public Loan save(LoanRequestDTO loanRequestDTO) {
        User user = userService.findById(loanRequestDTO.getUserId());
        Book book = bookService.findById(loanRequestDTO.getBookId());

        if (loanRequestDTO.getReturnDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Return date cannot be before loan date.");
        }

        if (book.getBookStatus().equals(BookStatus.BORROWED)) {
            throw new BookIsNotAvailableException("The book is borrowed");
        }
        book.lend();


        if (user.getActiveLoansQuantity() >= 5) {
            throw new UserLoanLimitExceededException("User has reached the maximum number of active loans (5).");
        }
        user.addActiveLoan();

        Loan loan = new Loan(user, book, loanRequestDTO.getReturnDate());
        return loanRepository.save(loan);
    }


    public List<Book> findLateBooks() {
        return loanRepository.findAll().stream()
                .filter(l -> l.getLoanStatus().equals(LoanStatus.ACTIVE))
                .filter(l -> l.getReturnDate().isBefore(LocalDate.now()))
                .map(Loan::getBook)
                .toList();
    }

    @Transactional
    public Loan finishLoan(Long id) {
        Loan loan = findById(id);

        if (loan.getLoanStatus().equals(LoanStatus.FINISHED)) {
            throw new LoanAlreadyFinishedException("Loan has already been finished!");
        }

        loan.finish();
        return loan;
    }
}
