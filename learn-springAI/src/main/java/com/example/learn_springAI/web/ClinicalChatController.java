package com.example.learn_springAI.web;

import com.example.learn_springAI.rag.RagService;
import com.example.learn_springAI.web.dto.ClinicalChatRequest;
import com.example.learn_springAI.web.dto.ClinicalChatResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/clinical-chat")
@RequiredArgsConstructor
public class ClinicalChatController {

    private final RagService ragService;

    @PostMapping("/messages")
    public ClinicalChatResponse postMessage(@Valid @RequestBody ClinicalChatRequest request) {
        return ragService.complete(request);
    }

    @PostMapping(value = "/messages/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter postMessageStream(@Valid @RequestBody ClinicalChatRequest request) {
        return ragService.stream(request);
    }

}
