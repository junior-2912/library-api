package com.junior.library.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<ValidationError> validationErrors = new ArrayList<>();

    public StandardError() {
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public List<ValidationError> getErrors() {
        return List.copyOf(validationErrors);
    }

    public void setErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
