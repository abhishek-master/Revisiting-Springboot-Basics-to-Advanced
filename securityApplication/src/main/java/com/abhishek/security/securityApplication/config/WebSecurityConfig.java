package com.abhishek.security.securityApplication.config;

import com.abhishek.security.securityApplication.OAuth2SuccessHandler;
import com.abhishek.security.securityApplication.filters.JwtAuthFilter;
import com.abhishek.security.securityApplication.filters.RequestResponseLoggerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Adding a new filter in the Security filter chain of Spring Security Step[2] {Adding the created filter to chain LINE }
@Configuration
@EnableWebSecurity //This annotation let us tell our Springboot that now we are going to configure the spring security
//filter chain. It tells that now we will be configuring the security stuff. SecurityFilterChain is one of those things.
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter ;
    private final RequestResponseLoggerFilter requestResponseLoggerFilter ;
    private final OAuth2SuccessHandler oAuth2SuccessHandler ;
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        /*
        * Request Matchers here are used to set access to the routes based on roles or say any route which needs
        * to be public.
        * */
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/posts","/auth/**", "/home.html/**", "/home.html").permitAll()
//                        .requestMatchers("/posts/**").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(requestResponseLoggerFilter, JwtAuthFilter.class)
                .oauth2Login(oauth2Config -> oauth2Config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );

                //.formLogin(Customizer.withDefaults());

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
    AuthenticationManager authenticationManager (AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
