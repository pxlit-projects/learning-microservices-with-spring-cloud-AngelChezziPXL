package be.pxl.services.shoppingcart.controller.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private final LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String path;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String errorMessage) {
        this();
        this.status = status;
        this.error = errorMessage;
    }

    public ApiError(HttpStatus status, String error, String path) {
        this();
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() { return path;}
}
