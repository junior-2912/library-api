package com.junior.library.controllers;

import com.junior.library.dto.BookRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookRequestDTO bookRequestDTO) {
        Book savedBook = bookService.saveBook(bookRequestDTO);
        return ResponseEntity.ok(savedBook);
    }
}
