package com.junior.library.util;

import com.junior.library.entities.Book;
import com.junior.library.entities.Loan;
import com.junior.library.entities.User;

import java.time.LocalDate;

public class LoanCreator {
    public static Loan createLoan() {
        User userTest = new User("User test", "user@gmail.com");

        Book bookTest = new Book("123456789", "A book for test", "Tester");

        return new Loan(userTest, bookTest, LocalDate.parse("2027-12-31"));
    }
}
