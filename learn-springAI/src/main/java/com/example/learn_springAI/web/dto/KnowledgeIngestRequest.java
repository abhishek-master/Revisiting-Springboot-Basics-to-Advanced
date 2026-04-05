package com.example.learn_springAI.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KnowledgeIngestRequest {

    @NotBlank
    private String text;

    private String source;

    private String topic;

    /**
     * Extra metadata merged into the document (optional).
     */
    private Map<String, Object> metadata;

}
