package com.example.learn_springAI.rag;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.rag")
public class RagProperties {

    /**
     * Number of vector chunks to inject into the prompt.
     */
    private int topK = 4;

    /**
     * Minimum similarity score (0–1) for retrieved documents.
     */
    private double similarityThreshold = 0.35;

    /**
     * Max characters total for concatenated retrieved context.
     */
    private int maxContextChars = 6000;

}
