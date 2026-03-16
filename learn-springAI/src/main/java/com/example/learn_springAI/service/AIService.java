package com.example.learn_springAI.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
* Creating a service where we connect to a LLM and pass a topic,
* Based on the topic it will return a Joke.
* */
@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient ;

    //Tripple quotes are used when we need to write text with breaked lines



    public String getAiMedicalSuggestions(String age, String diet, String state, String symptoms){

        String systemPrompt = """
            You are a Senior doctor with Phd level expertise in BDS -Dental- domain.
            You are dealing with Patient who is aged {age}, by diet he is {diet} and belongs from {state}
            Suggest what medical issues does these symptoms {symptoms} suggest.
            """;
        //Every thing in {} is a placeholder and when parsed by prompt template

        PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);
        String renderedText = promptTemplate.render(Map.of("age", age, "diet", diet, "state", state, "symptoms", symptoms));

        return chatClient.prompt()
                .user(renderedText)
                .call()
                .content();
    }


    public String getJoke(String topic){
        return chatClient.prompt()
                .system("You are a sarcastic Joker, give response in 5 lines")
                .user("Give me a joke on the topic : " + topic)
                .call()
                .content();
    }

    //user : USER'S Prompt
    //syste : SYSTEM's Prompt to set Guradrails or Basic outline
}
