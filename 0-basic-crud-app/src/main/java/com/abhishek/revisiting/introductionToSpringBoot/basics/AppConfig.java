package com.abhishek.revisiting.introductionToSpringBoot.basics;

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
