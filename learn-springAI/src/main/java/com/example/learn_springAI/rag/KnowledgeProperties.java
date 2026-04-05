package com.example.learn_springAI.rag;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.knowledge")
public class KnowledgeProperties {

    /**
     * When true, ingest bundled demo clinical snippets on startup (for client demos).
     */
    private boolean seedDemo = true;

    /**
     * Optional admin ingest of raw text (disabled by default in production).
     */
    private boolean adminIngestEnabled = true;

}
