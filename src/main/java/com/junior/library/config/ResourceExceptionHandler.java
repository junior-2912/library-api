package com.junior.library.config;

import com.junior.library.exceptions.BookIsNotAvailableException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(BookIsNotAvailableException.class)
    public ResponseEntity<StandardError> bookIsNotAvailable(BookIsNotAvailableException e, HttpServletRequest request) {
        StandardError error = new StandardError();

        error.setInstant(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

}
