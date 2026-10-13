package com.junior.library.controllers;

import com.junior.library.entities.Book;
import com.junior.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookResource.class)
class BookResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void findById_shouldReturnBookWhenIdExists() throws Exception {
        Book book = new Book("123456789", "O pequeno principe", "Antoine");
        book.setId(100L);

        Mockito.when(bookService.findById(100L)).thenReturn(book);

        mockMvc.perform(get("/books/{id}", 100L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100));
    }
}