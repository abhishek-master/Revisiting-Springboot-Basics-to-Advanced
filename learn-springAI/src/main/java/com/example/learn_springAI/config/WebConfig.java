package com.example.learn_springAI.config;

import com.example.learn_springAI.rag.ClinicalChatProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ClinicalChatProperties clinicalChatProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (clinicalChatProperties.getAllowedOrigins() == null || clinicalChatProperties.getAllowedOrigins().isEmpty()) {
            return;
        }
        registry.addMapping("/api/**")
                .allowedOrigins(clinicalChatProperties.getAllowedOrigins().toArray(String[]::new))
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

}
