package com.abhishek.auditorAwareAndCaching.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //We are not using Spring Security now so we will just hardcode the value.
        //Will update this in future when learning spring security
        //Get Security Context
        //Get Authentication
        //Get the principal
        //Get the username
        return Optional.of("Abhishek Sinha");
    }
}
