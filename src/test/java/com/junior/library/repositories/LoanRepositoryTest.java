package com.junior.library.repositories;

import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class LoanRepositoryTest {
    @Autowired
    private LoanRepository loanRepository;

    @Test
    void findById_shouldReturnAlreadyPersistedLoan() {
        Loan loan = createLoan();

        Long loanPersistedId = loanRepository.save(loan).getId();
        Loan foundLoan = loanRepository.findById(loanPersistedId)
                .orElseThrow();

        assertEquals(loan, foundLoan);
    }

    @Test
    void findById_shouldReturnEmptyOptionalWhenLoanDoesNotExist() {
        Loan loan = createLoan();

        Long wrongLoanPersistedId = loanRepository.save(loan).getId() + 1;

        assertTrue(loanRepository.findById(wrongLoanPersistedId).isEmpty());
    }

    @Test
    void save_shouldSaveLoanWhenSuccessful() {
        Loan loan = createLoan();

        Loan savedLoan = loanRepository.save(loan);

        assertThat(savedLoan)
                .isNotNull();

        assertThat(savedLoan.getId())
                .isNotNull();

        assertThat(savedLoan.getBook())
                .isEqualTo(loan.getBook());
    }

    private Loan createLoan() {
        User userTest = new User("User test", "user@gmail.com");
        Book bookTest = new Book("123456789", "A book for test", "Tester");
        return new Loan(userTest, bookTest, LocalDate.parse("2027-12-31"));
    }
}