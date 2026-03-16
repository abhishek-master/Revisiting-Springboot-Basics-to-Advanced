package com.abhishek.auditorAwareAndCaching.advices;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlingExceptions {

    @ExceptionHandler
    public Exception returnAnyException (Exception e) {
        return e ;
    }
}
