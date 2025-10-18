package com.abhishek.security.securityApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //This annotation let us tell our Springboot that now we are going to configure the spring security
//filter chain. It tells that now we will be configuring the security stuff. SecurityFilterChain is one of those things.
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        /*
        * Request Matchers here are used to set access to the routes based on roles or say any route which needs
        * to be public.
        * */
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/posts","/auth/**").permitAll()
                        .requestMatchers("/posts/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    /*
    * The below method is being used to create users in Memory and are maily used for testing cases
    * */
//    @Bean
//    UserDetailsService myInMemoryUserDetailsService(){
//        UserDetails normalUser = User
//                .withUsername("Abhishek")
//                .password(passwordEncoder().encode("Abhishek123"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User
//                .withUsername("AbhishekSinha")
//                .password(passwordEncoder().encode("Abhishek1234"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, admin);
//    }

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
