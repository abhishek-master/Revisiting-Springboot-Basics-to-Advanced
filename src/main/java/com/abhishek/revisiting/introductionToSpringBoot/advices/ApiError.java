package com.abhishek.revisiting.introductionToSpringBoot.advices;

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

/*
* What @Data does (big picture)

@Data is a shortcut annotation.

It generates all of this at compile time for your class:

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor


All in one line.*/
