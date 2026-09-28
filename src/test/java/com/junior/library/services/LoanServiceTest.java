package com.junior.library.services;

import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import com.junior.library.enums.BookStatus;
import com.junior.library.exceptions.BookIsNotAvailableException;
import com.junior.library.exceptions.UserLoanLimitExceededException;
import com.junior.library.repositories.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.Assert;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void shouldAllowLoanWhenUserHasLessThanFiveActiveLoans(int userActiveLoans) {
        UserService mockUserService = mock(UserService.class);
        BookService mockBookService = mock(BookService.class);
        LoanRepository mockLoanRepository = mock(LoanRepository.class);

        LoanService loanService = new LoanService(mockLoanRepository, mockBookService, mockUserService);
        User user = new User("Lebron James", "lebron@gmail.com");
        user.setId(100L);
        user.setActiveLoansQuantity(userActiveLoans);

        Book book = new Book("12345672134", "Ze Bucetinha", "maria priquito");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(100L);

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO(100L, 100L, LocalDate.parse("2026-10-23"));

        when(mockUserService.findById(100L)).thenReturn(user);
        when(mockBookService.findById(100L)).thenReturn(book);

        loanService.save(loanRequestDTO);

        Assertions.assertEquals(userActiveLoans + 1, user.getActiveLoansQuantity());
        verify(mockLoanRepository).save(any(Loan.class));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 10, 100})
    public void shouldThrowsExceptionWhenUserHas5OrMoreActiveLoans(int userActiveLoans) {
        UserService mockUserService = mock(UserService.class);
        BookService mockBookService = mock(BookService.class);
        LoanRepository mockLoanRepository = mock(LoanRepository.class);

        LoanService loanService = new LoanService(mockLoanRepository, mockBookService, mockUserService);
        User user = new User("Lebron James", "lebron@gmail.com");
        user.setId(100L);
        user.setActiveLoansQuantity(userActiveLoans);

        Book book = new Book("12345672134", "Ze Bucetinha", "maria priquito");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(100L);

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO(100L, 100L, LocalDate.parse("2026-10-23"));

        when(mockUserService.findById(100L)).thenReturn(user);
        when(mockBookService.findById(100L)).thenReturn(book);

        Assertions.assertThrows(UserLoanLimitExceededException.class, () -> loanService.save(loanRequestDTO));
        verify(mockLoanRepository, never()).save(any());
    }

    @Test
    public void shouldThrowsExceptionWhenReturnDateisBeforeThenLoanDate() {
        UserService mockUserService = mock(UserService.class);
        BookService mockBookService = mock(BookService.class);
        LoanRepository mockLoanRepository = mock(LoanRepository.class);

        LoanService loanService = new LoanService(mockLoanRepository, mockBookService, mockUserService);
        User user = new User("Lebron James", "lebron@gmail.com");
        user.setId(100L);
        user.setActiveLoansQuantity(4);

        Book book = new Book("12345672134", "Ze Bucetinha", "maria priquito");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(100L);

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO(100L, 100L, LocalDate.parse("2026-09-23"));

        when(mockUserService.findById(100L)).thenReturn(user);
        when(mockBookService.findById(100L)).thenReturn(book);

        Assertions.assertThrows(IllegalArgumentException.class, () -> loanService.save(loanRequestDTO));

        verify(mockLoanRepository, never()).save(any(Loan.class));
    }

    @Test
    public void shouldSaveLoanWhenBookIsAvailable() {
        UserService mockUserService = mock(UserService.class);
        BookService mockBookService = mock(BookService.class);
        LoanRepository mockLoanRepository = mock(LoanRepository.class);

        LoanService loanService = new LoanService(mockLoanRepository, mockBookService, mockUserService);
        User user = new User("Lebron James", "lebron@gmail.com");
        user.setId(100L);
        user.setActiveLoansQuantity(4);

        Book book = new Book("12345672134", "Ze Bucetinha", "maria priquito");
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setId(100L);

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO(100L, 100L, LocalDate.parse("2026-09-30"));

        when(mockUserService.findById(100L)).thenReturn(user);
        when(mockBookService.findById(100L)).thenReturn(book);

        loanService.save(loanRequestDTO);

        Assertions.assertEquals(BookStatus.BORROWED, book.getBookStatus());
        verify(mockLoanRepository).save(any(Loan.class));
    }
}