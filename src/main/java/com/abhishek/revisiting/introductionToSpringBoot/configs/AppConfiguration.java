package com.abhishek.revisiting.introductionToSpringBoot.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
