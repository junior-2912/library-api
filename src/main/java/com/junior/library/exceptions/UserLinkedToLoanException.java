package com.junior.library.exceptions;

public class UserLinkedToLoanException extends RuntimeException {
    public UserLinkedToLoanException(String message) {
        super(message);
    }
}
