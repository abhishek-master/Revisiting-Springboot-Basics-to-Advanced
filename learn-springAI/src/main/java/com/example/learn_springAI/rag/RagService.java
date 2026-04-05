package com.example.learn_springAI.rag;

import com.example.learn_springAI.web.dto.ClinicalChatRequest;
import com.example.learn_springAI.web.dto.ClinicalChatResponse;
import com.example.learn_springAI.web.dto.RetrievedSourceDto;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.task.TaskExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RagService {

    private static final String SYSTEM_PROMPT = """
            You are a clinical decision-support assistant helping a licensed physician during a fast outpatient visit.
            You are NOT diagnosing. Give concise, actionable suggestions: differentials to consider, history/exam \
            areas to review, and safety red flags when relevant.
            When INTERNAL CONTEXT excerpts are provided below, ground your suggestions in them and name the source \
            label when you use them. If INTERNAL CONTEXT is empty or not relevant, say so briefly and still offer \
            general clinical reasoning without inventing institutional policies.
            Do not address the patient directly. Use professional tone. Keep answers short unless the question \
            clearly needs detail.""";

    private final VectorStore vectorStore;
    private final ClinicalLlmClient llmClient;
    private final RagProperties ragProperties;
    private final ClinicalChatProperties clinicalChatProperties;
    private final TaskExecutor applicationTaskExecutor;

    public RagService(VectorStore vectorStore,
                      ClinicalLlmClient llmClient,
                      RagProperties ragProperties,
                      ClinicalChatProperties clinicalChatProperties,
                      @Qualifier("applicationTaskExecutor") TaskExecutor applicationTaskExecutor) {
        this.vectorStore = vectorStore;
        this.llmClient = llmClient;
        this.ragProperties = ragProperties;
        this.clinicalChatProperties = clinicalChatProperties;
        this.applicationTaskExecutor = applicationTaskExecutor;
    }

    public ClinicalChatResponse complete(ClinicalChatRequest request) {
        List<Document> documents = retrieve(request);
        String userPrompt = buildUserPrompt(request, documents);
        String reply = llmClient.complete(SYSTEM_PROMPT, userPrompt);
        return ClinicalChatResponse.builder()
                .reply(reply)
                .retrievedSources(toSources(documents))
                .disclaimer(clinicalChatProperties.getDisclaimer())
                .conversationId(request.getConversationId())
                .flowType(request.getFlowType())
                .build();
    }

    public SseEmitter stream(ClinicalChatRequest request) {
        List<Document> documents = retrieve(request);
        String userPrompt = buildUserPrompt(request, documents);
        SseEmitter emitter = new SseEmitter(120_000L);
        applicationTaskExecutor.execute(() -> {
            try {
                llmClient.stream(SYSTEM_PROMPT, userPrompt).subscribe(
                        chunk -> {
                            if (chunk == null || chunk.isEmpty()) {
                                return;
                            }
                            try {
                                emitter.send(SseEmitter.event().data(chunk));
                            } catch (IOException e) {
                                emitter.completeWithError(e);
                            }
                        },
                        emitter::completeWithError,
                        emitter::complete
                );
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    List<Document> retrieve(ClinicalChatRequest request) {
        String query = buildRetrievalQuery(request);
        return vectorStore.similaritySearch(SearchRequest.builder()
                .query(query)
                .topK(ragProperties.getTopK())
                .similarityThreshold(ragProperties.getSimilarityThreshold())
                .build());
    }

    String buildRetrievalQuery(ClinicalChatRequest request) {
        StringBuilder sb = new StringBuilder(request.getMessage().trim());
        if (request.getStructuredHistory() != null && !request.getStructuredHistory().isEmpty()) {
            sb.append(" ").append(String.join(", ", request.getStructuredHistory()));
        }
        if (request.getCurrentMedications() != null && !request.getCurrentMedications().isEmpty()) {
            sb.append(" Medications: ").append(String.join(", ", request.getCurrentMedications()));
        }
        if (request.getAttachmentsSummary() != null && !request.getAttachmentsSummary().isBlank()) {
            sb.append(" ").append(request.getAttachmentsSummary().trim());
        }
        return sb.toString();
    }

    private String buildUserPrompt(ClinicalChatRequest request, List<Document> documents) {
        String contextBlock = formatContext(documents);
        StringBuilder user = new StringBuilder();
        user.append("Physician message (symptoms / patient narrative):\n").append(request.getMessage().trim());
        if (request.getStructuredHistory() != null && !request.getStructuredHistory().isEmpty()) {
            user.append("\n\nStructured history flags: ")
                    .append(String.join(", ", request.getStructuredHistory()));
        }
        if (request.getCurrentMedications() != null && !request.getCurrentMedications().isEmpty()) {
            user.append("\n\nCurrent medications (from chart): ")
                    .append(String.join(", ", request.getCurrentMedications()));
        }
        if (request.getAttachmentsSummary() != null && !request.getAttachmentsSummary().isBlank()) {
            user.append("\n\nInvestigations summary (pre-extracted text):\n")
                    .append(request.getAttachmentsSummary().trim());
        }
        if (request.getFlowType() != null && !request.getFlowType().isBlank()) {
            user.append("\n\nVisit flow: ").append(request.getFlowType().trim());
        }
        user.append("\n\nINTERNAL CONTEXT (retrieved excerpts — cite by source when used):\n");
        user.append(contextBlock.isBlank() ? "(none)" : contextBlock);
        return user.toString();
    }

    private String formatContext(List<Document> documents) {
        if (documents == null || documents.isEmpty()) {
            return "";
        }
        int budget = ragProperties.getMaxContextChars();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Document doc : documents) {
            Map<String, Object> meta = doc.getMetadata() != null ? doc.getMetadata() : Map.of();
            String source = Objects.toString(meta.getOrDefault("source", "source-" + i), "source-" + i);
            String body = doc.getText() != null ? doc.getText() : "";
            String section = "[" + source + "]\n" + body + "\n\n";
            if (sb.length() + section.length() > budget) {
                break;
            }
            sb.append(section);
            i++;
        }
        return sb.toString().trim();
    }

    private List<RetrievedSourceDto> toSources(List<Document> documents) {
        if (documents == null || documents.isEmpty()) {
            return List.of();
        }
        List<RetrievedSourceDto> out = new ArrayList<>();
        int i = 1;
        for (Document doc : documents) {
            Map<String, Object> meta = doc.getMetadata() != null ? doc.getMetadata() : Map.of();
            String source = Objects.toString(meta.getOrDefault("source", "source-" + i), "source-" + i);
            String text = doc.getText() != null ? doc.getText() : "";
            String excerpt = text.length() > 400 ? text.substring(0, 400) + "…" : text;
            out.add(new RetrievedSourceDto(source, excerpt));
            i++;
        }
        return out;
    }

    /**
     * Ingest a single knowledge chunk (admin / demo).
     */
    public void ingestChunk(String text, Map<String, Object> metadata) {
        vectorStore.add(List.of(new Document(text, metadata != null ? metadata : Map.of())));
    }

}
