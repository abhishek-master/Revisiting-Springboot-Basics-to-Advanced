package com.example.learn_springAI.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
    /*
    * It will create a chat Client bean for Gemini based on dependency we have used we don't need to write it's
    * implementation. We can directly return the client and use in our application.
    * */

}
