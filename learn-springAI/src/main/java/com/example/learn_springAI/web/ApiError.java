package com.example.learn_springAI.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    Instant timestamp;
    int status;
    String error;
    String message;
    String path;
    List<FieldViolation> fieldErrors;

    @Value
    @Builder
    public static class FieldViolation {
        String field;
        String message;
    }

}
