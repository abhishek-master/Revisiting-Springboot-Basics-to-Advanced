package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.basics;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "deploy.env", havingValue = "development")
public class DevDB implements DB {
    public String getData (){
        return "DEV DATA !" ;
    }

}
