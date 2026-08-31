package com.junior.library.services;

import com.junior.library.dto.BookRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.enums.BookStatus;
import com.junior.library.exceptions.BookIsBorrowedException;
import com.junior.library.exceptions.ResourceNotFoundException;
import com.junior.library.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
    }

    public Book saveBook(BookRequestDTO bookRequestDTO) {
        return bookRepository.save(new Book(
                bookRequestDTO.getIsbn(),
                bookRequestDTO.getTitle(),
                bookRequestDTO.getAuthor()));
    }

    @Transactional
    public void delete(Long id) {
        Book book = findById(id);

        if (book.getBookStatus().equals(BookStatus.BORROWED)) {
            throw new BookIsBorrowedException("Cannot delete a borrowed book.");
        }

        bookRepository.delete(book);
    }
}
