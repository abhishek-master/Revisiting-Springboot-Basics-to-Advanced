package com.abhishek.revisiting.introductionToSpringBoot.advices;


import com.abhishek.revisiting.introductionToSpringBoot.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerException (Exception e){
        ApiError apiErrors = ApiError.builder().message(e.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return buildErrorResponseEntity(apiErrors);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse<?>> handleException (ResourceNotFound e){
        ApiError apiError = ApiError.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleWrongArgumentException(MethodArgumentNotValidException e) {
        ApiError apiError = ApiError.builder().message("Validation failing for expected data")
                .status(HttpStatus.BAD_REQUEST)
                .errors(e.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map((error) -> error.getDefaultMessage())
                        .toList())
                .build();

        return  buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

}
