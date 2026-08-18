package com.junior.library.test;

import com.junior.library.entitie.Book;
import com.junior.library.entitie.User;
import com.junior.library.enums.BookStatus;
import com.junior.library.repository.BookRepository;
import com.junior.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Book b1 = new Book(null, "Código Limpo", "Robert C. Martin", BookStatus.AVAILABLE);
        Book b2 = new Book(null, "O Programador Pragmático", "Andrew Hunt e David Thomas", BookStatus.AVAILABLE);
        Book b3 = new Book(null, "Entendendo Algoritmos", "Aditya T. Bhargava", BookStatus.AVAILABLE);
        Book b4 = new Book(null, "O Codificador Limpo", "Robert C. Martin", BookStatus.AVAILABLE);
        Book b5 = new Book(null, "Introdução à Programação com Python", "Nilo Ney Coutinho Menezes", BookStatus.AVAILABLE);

        User u1 = new User(null, "Jhon Carter", null);
        User u2 = new User(null, "Martin Storm", null);
        User u3 = new User(null, "Jason Jackson", null);
        User u4 = new User(null, "Mariah Robert", null);
        User u5 = new User(null, "Will Clawer", null);

        bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));
        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
    }
}
