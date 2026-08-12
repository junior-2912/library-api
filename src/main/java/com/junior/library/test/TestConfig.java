package com.junior.library.test;

import com.junior.library.entitie.Book;
import com.junior.library.enums.BookStatus;
import com.junior.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Book b1 = new Book(null, "Clean Code", "Robert Smith", BookStatus.AVAILABLE);

        bookRepository.save(b1);
    }
}
