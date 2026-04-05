package com.example.learn_springAI.web;

import com.example.learn_springAI.rag.KnowledgeProperties;
import com.example.learn_springAI.rag.RagService;
import com.example.learn_springAI.web.dto.KnowledgeIngestRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/knowledge")
@RequiredArgsConstructor
public class AdminKnowledgeController {

    private final RagService ragService;
    private final KnowledgeProperties knowledgeProperties;

    @PostMapping("/ingest")
    public ResponseEntity<Void> ingest(@Valid @RequestBody KnowledgeIngestRequest request) {
        if (!knowledgeProperties.isAdminIngestEnabled()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Map<String, Object> meta = new HashMap<>();
        if (request.getMetadata() != null) {
            meta.putAll(request.getMetadata());
        }
        if (request.getSource() != null && !request.getSource().isBlank()) {
            meta.put("source", request.getSource().trim());
        }
        if (request.getTopic() != null && !request.getTopic().isBlank()) {
            meta.put("topic", request.getTopic().trim());
        }
        ragService.ingestChunk(request.getText(), meta);
        return ResponseEntity.accepted().build();
    }

}
