package com.junior.library.exceptions;

public class BookIsNotAvailableException extends RuntimeException {
    public BookIsNotAvailableException(String message) {
        super(message);
    }
}
