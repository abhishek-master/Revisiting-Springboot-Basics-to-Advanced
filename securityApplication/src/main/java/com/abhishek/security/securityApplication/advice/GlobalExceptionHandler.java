package com.abhishek.security.securityApplication.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> globalExceptionHandler (Exception e) {
        ApiError error = new ApiError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<>(error, error.getStatusCode());
    }
}
