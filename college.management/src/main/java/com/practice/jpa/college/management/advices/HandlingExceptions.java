package com.practice.jpa.college.management.advices;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlingExceptions {

    @ExceptionHandler
    public Exception returnAnyException (Exception e) {
        return e ;
    }
}
