package com.junior.library.exceptions;

public class UserLoanLimitExceededException extends RuntimeException {
    public UserLoanLimitExceededException(String message) {
        super(message);
    }
}
