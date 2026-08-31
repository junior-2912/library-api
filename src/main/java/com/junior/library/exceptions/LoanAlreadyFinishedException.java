package com.junior.library.exceptions;

public class LoanAlreadyFinishedException extends RuntimeException {
    public LoanAlreadyFinishedException(String message) {
        super(message);
    }
}
