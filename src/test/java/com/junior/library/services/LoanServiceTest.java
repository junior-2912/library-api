package com.junior.library.services;

import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.enums.BookStatus;
import com.junior.library.exceptions.BookIsNotAvailableException;
import com.junior.library.repositories.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Test
    public void shouldNotSaveABorrowedBook() {
        UserService mockUserService = mock(UserService.class);
        BookService mockBookService = mock(BookService.class);
        LoanRepository mockLoanRepository = mock(LoanRepository.class);

        LoanService loanService = new LoanService(mockLoanRepository, mockBookService, mockUserService);

        Book book = new Book("12345672134", "Ze Bucetinha", "maria priquito");
        book.setBookStatus(BookStatus.BORROWED);
        book.setId(100L);

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO(4L, 100L, LocalDate.parse("2026-12-05"));
        when(mockBookService.findById(100L)).thenReturn(book);

        Assertions.assertThrows(BookIsNotAvailableException.class, () -> loanService.save(loanRequestDTO));

        verify(mockLoanRepository, never()).save(any());
    }
}