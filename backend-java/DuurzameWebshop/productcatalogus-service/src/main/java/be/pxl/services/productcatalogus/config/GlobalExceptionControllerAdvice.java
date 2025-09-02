package be.pxl.services.productcatalogus.config;

import be.pxl.services.productcatalogus.controller.dto.ApiError;
import be.pxl.services.productcatalogus.exception.ConflictException;
import be.pxl.services.productcatalogus.exception.ResourceNotFoundExeception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler({ResourceNotFoundExeception.class})
    public ResponseEntity<Object> handleRsourceNotFoundExeception(ResourceNotFoundExeception ex, HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleConflictException(ConflictException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError error = new ApiError(status, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {
        String message = methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                message,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }
}
