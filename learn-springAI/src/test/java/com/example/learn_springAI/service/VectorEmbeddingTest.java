package com.example.learn_springAI.service;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VectorEmbeddingTest {

    @Autowired
    private VectorEmbedding vectorEmbedding;


    @Test
    void ingestDataToVectorStore() {
        vectorEmbedding.ingestDataToVectorStore("This is a unit test case's testing !!!");
        //vectorEmbedding.ingestDataToVectorStore("I am a dog !!");
    }

    @Test
    void testSimilaritySearch() {
        var res  = vectorEmbedding.similaritySearch("Robot Movie");
        for(var temp: res){
            System.out.println(temp.toString());
        }
    }
}