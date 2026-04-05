package com.example.learn_springAI.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClinicalChatRequest {

    @NotBlank(message = "message is required")
    private String message;

    private List<String> structuredHistory;

    private List<String> currentMedications;

    private String flowType;

    private String visitCorrelationId;

    private String conversationId;

    /**
     * Pre-extracted text from labs/imaging when another HMS service provides it.
     */
    private String attachmentsSummary;

}
