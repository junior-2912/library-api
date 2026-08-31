package com.junior.library.exceptions;

public class BookIsBorrowedException extends RuntimeException {
    public BookIsBorrowedException(String message) {
        super(message);
    }
}
