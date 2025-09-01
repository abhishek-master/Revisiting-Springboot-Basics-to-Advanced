package com.abhishek.revisiting.introductionToSpringBoot.exceptions;

import java.util.Arrays;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(){}
    public ResourceNotFound(String message){
        super(message);
    }

    public String getStackTraceString() {
        StackTraceElement[] STE = super.getStackTrace();
        return Arrays.toString(STE);
    }
}
