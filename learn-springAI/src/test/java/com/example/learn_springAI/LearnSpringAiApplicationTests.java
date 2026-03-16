package com.example.learn_springAI;

import com.example.learn_springAI.service.AIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringAiApplicationTests {

    @Autowired
    private AIService aiService ;

    @Test
	void contextLoads() {
	}

    @Test
    public void testGetJoke(){
        var joke  = aiService.getJoke("bugs in software");
        System.out.println(joke);
    }

    @Test
    public void testgetAiMedicalSuggestion(){
        var aiDiagnosis = aiService.getAiMedicalSuggestions("29", "vegetarian", "Uttar Pradesh", "The patient reports a sharp, momentary pain when eating sugary snacks or drinking cold water. A physical check reveals a small, dark pit or \"sticky\" spot on the biting surface of a molar.");
        System.out.println(aiDiagnosis);
    }

}
