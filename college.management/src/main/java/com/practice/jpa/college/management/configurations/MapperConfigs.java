package com.practice.jpa.college.management.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {

    @Bean
    ModelMapper getModelMapper() {return new ModelMapper();}
}
