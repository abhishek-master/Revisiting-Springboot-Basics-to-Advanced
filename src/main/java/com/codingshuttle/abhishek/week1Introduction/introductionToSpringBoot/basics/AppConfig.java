package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.basics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    String temp = "Some Secret Key for third party" ;
    @Bean
    Apple haveApple(){
        return new Apple(temp);
    }
}
