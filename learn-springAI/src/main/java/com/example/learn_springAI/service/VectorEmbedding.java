package com.example.learn_springAI.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VectorEmbedding {

    private final EmbeddingModel embeddingModel ;
    private final VectorStore vectorStore ;

    public float[] getEmbedding(String text){
        return embeddingModel.embed(text);
    }

    public void ingestDataToVectorStore_OLD(String text){
        //To store data to a vector store we need to convert it into a DocumentObject
        Document document = new Document(text);
        //Since the text is converted to a document format we can store it to the vector store.
        vectorStore.add(List.of(document));
    }


    public void ingestDataToVectorStore(String text){
        //We can provide our Document with a map, and this map act as metadata in the vector store database.
        //When you add these documents they will be saved/added/ingested parallely, that is you add 1 Document or List
        // of 10 document, they are not added one at a time but parallely  added using threads.

        //Using metadata (here Map.of(K, V)) in here is that we can use is to filter more result.  Like let's say we need
        //a movie of the genre "Action" so in this case metadata comes handy.
        List<Document> movies = List.of(
                new Document(
                        "A thief who steals corporate secrets through dream-sharing technology is tasked with planting an idea into a target's mind",
                        Map.of("title", "Inception", "genre", "Sci-Fi", "year", 2010)
                ),

                new Document(
                        "Batman faces a criminal mastermind known as the Joker who spreads chaos across Gotham City",
                        Map.of("title", "The Dark Knight", "genre", "Action", "year", 2008)
                ),

                new Document(
                        "A team of explorers travel through a wormhole in space to ensure humanity's survival",
                        Map.of("title", "Interstellar", "genre", "Sci-Fi", "year", 2014)
                ),

                new Document(
                        "A computer hacker learns about the true nature of reality and joins a rebellion against intelligent machines",
                        Map.of("title", "The Matrix", "genre", "Sci-Fi", "year", 1999)
                ),

                new Document(
                        "An insomniac office worker forms an underground fight club that evolves into something much bigger",
                        Map.of("title", "Fight Club", "genre", "Drama", "year", 1999)
                ),

                new Document(
                        "The story of a kind-hearted man who experiences major historical events while living an extraordinary life",
                        Map.of("title", "Forrest Gump", "genre", "Drama", "year", 1994)
                ),

                new Document(
                        "Two imprisoned men form a deep friendship and find hope and redemption over many years",
                        Map.of("title", "The Shawshank Redemption", "genre", "Drama", "year", 1994)
                ),

                new Document(
                        "A betrayed Roman general seeks revenge against the emperor who murdered his family",
                        Map.of("title", "Gladiator", "genre", "Action", "year", 2000)
                ),

                new Document(
                        "A young couple from different social classes fall in love aboard a doomed ocean liner",
                        Map.of("title", "Titanic", "genre", "Romance", "year", 1997)
                ),

                new Document(
                        "The Avengers assemble to reverse the destruction caused by Thanos and restore balance to the universe",
                        Map.of("title", "Avengers: Endgame", "genre", "Superhero", "year", 2019)
                )
        );

        vectorStore.add(movies);

    }

    public List<Document> similaritySearch (String text){
        // return vectorStore.similaritySearch(text);
        return vectorStore.similaritySearch(SearchRequest.builder()
                .query(text)
                .topK(3)
                .similarityThreshold(0.5)
                .build());
    }

    //What does similarityThreshold mean?
    //It is a value between 0 and 1. Which how much similar the document it with the query.
    // (0 --> least similar, 1 --> most similar)
    //It means that the similarity score of the document should be greater than the similarityThreshold.
    //If the similarity score is less than the similarityThreshold, then the document is not returned.






}
