package com.example.learn_springAI.dto;

public record MedicalResponse(
        String text,
        String category,
        Double rating,
        Boolean isNSFW){

}

//NSFW : Not safe for work
