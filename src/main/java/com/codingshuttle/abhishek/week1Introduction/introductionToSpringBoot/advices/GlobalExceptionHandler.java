package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.advices;


import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerException (Exception e){
        ApiError apiErrors = ApiError.builder().message(e.getMessage()).status("INTERNAL SERVER ERROR").build();
        return new ResponseEntity<>(apiErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleException (ResourceNotFound e){
        ApiError apiError = ApiError.builder().message(e.getMessage()).status("NOT FOUND").build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleWrongArgumentException(MethodArgumentNotValidException e) {
        ApiError apiError = ApiError.builder().message("Validation failing for expected data")
                .status("BAD REQUEST")
                .errors(e.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map((error) -> error.getDefaultMessage())
                        .toList())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
