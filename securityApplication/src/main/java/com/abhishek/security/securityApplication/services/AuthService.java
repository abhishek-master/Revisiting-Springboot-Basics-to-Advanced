package com.abhishek.security.securityApplication.services;

import com.abhishek.security.securityApplication.dto.LoginDTO;
import com.abhishek.security.securityApplication.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager ;
    private final JwtService jwtService ;

    public String login(LoginDTO loginDTO) {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return token ;
    }
}
