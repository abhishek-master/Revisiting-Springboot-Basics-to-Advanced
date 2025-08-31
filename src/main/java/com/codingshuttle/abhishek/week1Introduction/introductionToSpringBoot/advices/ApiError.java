package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    String message;
    HttpStatus status;
    List<String> errors ;

}
