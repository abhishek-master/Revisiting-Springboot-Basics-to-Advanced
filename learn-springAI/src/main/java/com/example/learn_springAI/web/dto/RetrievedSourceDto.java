package com.example.learn_springAI.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RetrievedSourceDto(String source, String excerpt) {
}
