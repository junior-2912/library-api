package com.junior.library.util;

import com.junior.library.dto.LoanRequestDTO;
import com.junior.library.entities.Loan;

import java.time.LocalDate;

public class LoanRequestDtoCreator {
    public static LoanRequestDTO create() {
        Loan loan = LoanCreator.createLoan();
        return new LoanRequestDTO(loan.getUser().getId(), loan.getBook().getId(), loan.getReturnDate());
    }
}
