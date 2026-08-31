package com.junior.library.config;

import com.junior.library.exceptions.BookIsNotAvailableException;
import com.junior.library.exceptions.LoanAlreadyFinishedException;
import com.junior.library.exceptions.ResourceNotFoundException;
import com.junior.library.exceptions.UserLoanLimitExceededException;
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

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(LoanAlreadyFinishedException.class)
    public ResponseEntity<StandardError> loanAlreadyFinished(LoanAlreadyFinishedException e, HttpServletRequest request) {
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserLoanLimitExceededException.class)
    public ResponseEntity<StandardError> userLoanLimitExceeded(UserLoanLimitExceededException e, HttpServletRequest request) {
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
