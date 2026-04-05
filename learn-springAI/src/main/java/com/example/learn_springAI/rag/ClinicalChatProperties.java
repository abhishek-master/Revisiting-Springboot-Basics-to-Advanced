package com.example.learn_springAI.rag;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "app.clinical-chat")
public class ClinicalChatProperties {

    private String disclaimer = "This output is clinical decision support only; it is not a diagnosis. "
            + "Verify with examination, history, and institutional guidelines.";

    private List<String> allowedOrigins = new ArrayList<>(List.of("http://localhost:4200"));

}
