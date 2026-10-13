package com.junior.library.repositories;

import com.junior.library.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    void findByTitle_shouldReturnListOfBooksWhenTheyContainsIgnoreCaseTitle() {
        Book book = createBook();
        repository.save(book);

        List<Book> peqUEno = repository.findAllByTitleContainingIgnoreCase("peqUEno");

        assertEquals(List.of(book), peqUEno);
    }

    @Test
    void findByTitle_shouldReturnEmptyListWhenNoTitleMatches() {
        Book book = createBook();
        repository.save(book);

        assertTrue(repository.findAllByTitleContainingIgnoreCase("grande").isEmpty());
    }

    @Test
    void findByTitle_shouldReturnAllBooksThatMatchTitle() {
        List<Book> books = createBooks();
        repository.saveAll(books);

        List<Book> foundBooks = repository.findAllByTitleContainingIgnoreCase("prinCIPe");

        assertEquals(books.size(), foundBooks.size());
        for (Book b : books) {
            assertTrue(foundBooks.contains(b));
        }
    }

    private Book createBook() {
        return new Book("123456789", "O pequeno principe", "Antoine");
    }

    private List<Book> createBooks() {
        return Arrays.asList(
                new Book("123456789", "O pequeno principe", "Antoine"),
                new Book("987654321", "O medio principe", "Aline"),
                new Book("123987645", "O grande principe", "Robert")
        );
    }
}