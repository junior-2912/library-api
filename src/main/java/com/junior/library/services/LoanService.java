package com.junior.library.services;

import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.enums.BookStatus;
import com.junior.library.enums.LoanStatus;
import com.junior.library.exceptions.BookIsNotAvailableException;
import com.junior.library.repositories.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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
        return loanRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Loan not found!"));
    }

    @Transactional
    public Loan save(Loan loan) {

        // Regra de negócio: Livro não pode ser emprestado novamente enquanto o empréstimo estiver ativo.
        Book loanBook = bookService.findById(loan.getBook().getIsbn());
        if (loanBook.getBookStatus().equals(BookStatus.BORROWED)) {
            throw new BookIsNotAvailableException("The book is borrowed");
        }
        loanBook.lend();

        User user = userService.findById(loan.getUser().getId());

        loan.setLoanStatus(LoanStatus.ACTIVE);
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
        loan.finish(); //com esse metodo loanStatus = .FINISHED -> e dentro de finish, chama o metodo refund do livro, que faz bookStatus = .AVAILABLE
        return loan;
    }
}
