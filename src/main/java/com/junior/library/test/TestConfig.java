package com.junior.library.test;

import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.enums.BookStatus;
import com.junior.library.enums.LoanStatus;
import com.junior.library.repositories.BookRepository;
import com.junior.library.repositories.LoanRepository;
import com.junior.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void run(String... args) throws Exception {
        Book b1 = new Book(null, "Código Limpo", "Robert C. Martin", BookStatus.AVAILABLE);
        Book b2 = new Book(null, "O Programador Pragmático", "Andrew Hunt e David Thomas", BookStatus.AVAILABLE);
        Book b3 = new Book(null, "Entendendo Algoritmos", "Aditya T. Bhargava", BookStatus.AVAILABLE);
        Book b4 = new Book(null, "O Codificador Limpo", "Robert C. Martin", BookStatus.AVAILABLE);
        Book b5 = new Book(null, "Introdução à Programação com Python", "Nilo Ney Coutinho Menezes", BookStatus.AVAILABLE);

        User u1 = new User(null, "Jhon Carter");
        User u2 = new User(null, "Martin Storm");
        User u3 = new User(null, "Jason Jackson");
        User u4 = new User(null, "Mariah Robert");
        User u5 = new User(null, "Will Clawer");

        Loan l1 = new Loan(null, u2, b4, LocalDate.now(), LocalDate.parse("2026-10-01"), LoanStatus.ACTIVE);
        Loan l2 = new Loan(null, u1, b2, LocalDate.now(), LocalDate.parse("2026-11-01"), LoanStatus.ACTIVE);
        Loan l3 = new Loan(null, u5, b5, LocalDate.now(), LocalDate.parse("2026-12-01"), LoanStatus.ACTIVE);


        bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));
        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
        loanRepository.saveAll(Arrays.asList(l1, l2, l3));
    }
}
