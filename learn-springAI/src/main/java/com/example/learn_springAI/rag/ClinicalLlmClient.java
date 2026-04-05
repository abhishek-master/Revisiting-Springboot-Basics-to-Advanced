package com.example.learn_springAI.rag;

import reactor.core.publisher.Flux;

/**
 * Abstraction over the chat model for testability and swapping providers.
 */
public interface ClinicalLlmClient {

    String complete(String systemPrompt, String userPrompt);

    Flux<String> stream(String systemPrompt, String userPrompt);

}
