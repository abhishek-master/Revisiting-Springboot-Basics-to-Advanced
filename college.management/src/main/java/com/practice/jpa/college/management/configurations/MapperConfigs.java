package com.practice.jpa.college.management.configurations;

import com.practice.jpa.college.management.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAware") //Now the JPA is attached to the auditor
public class MapperConfigs {

    @Bean
    ModelMapper getModelMapper() {return new ModelMapper();}

    @Bean
    AuditorAware<String> getAuditorAware () {return new AuditorAwareImpl() ;}
}
