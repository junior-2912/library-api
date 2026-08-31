package com.junior.library.exceptions;

public class LoanIsActiveException extends RuntimeException {
    public LoanIsActiveException(String message) {
        super(message);
    }
}
