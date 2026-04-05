package com.example.learn_springAI;

import com.example.learn_springAI.rag.ClinicalChatProperties;
import com.example.learn_springAI.rag.KnowledgeProperties;
import com.example.learn_springAI.rag.RagProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RagProperties.class, ClinicalChatProperties.class, KnowledgeProperties.class})
public class LearnSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAiApplication.class, args);
	}

}
