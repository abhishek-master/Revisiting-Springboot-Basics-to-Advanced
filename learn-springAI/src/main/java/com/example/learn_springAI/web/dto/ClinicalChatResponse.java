package com.example.learn_springAI.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ClinicalChatResponse {

    private String reply;
    private List<RetrievedSourceDto> retrievedSources;
    private String disclaimer;
    private String conversationId;
    private String flowType;

}
