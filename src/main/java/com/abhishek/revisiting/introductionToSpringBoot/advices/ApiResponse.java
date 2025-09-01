package com.abhishek.revisiting.introductionToSpringBoot.advices;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private T data ;

    @JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timeStamp ;

    private ApiError error;

    public ApiResponse () {
        this.timeStamp = LocalDateTime.now() ;
    }

    public ApiResponse(T apiRes){
        this();
        this.data = apiRes ;
    }

    public ApiResponse(ApiError receivedError){
        this();
        this.error = receivedError ;
    }
}
