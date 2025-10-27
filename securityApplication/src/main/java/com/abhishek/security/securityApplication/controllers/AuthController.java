package com.abhishek.security.securityApplication.controllers;


import com.abhishek.security.securityApplication.dto.LoginDTO;
import com.abhishek.security.securityApplication.dto.LoginResponseDto;
import com.abhishek.security.securityApplication.dto.SignUpDTO;
import com.abhishek.security.securityApplication.dto.UserDTO;
import com.abhishek.security.securityApplication.services.AuthService;
import com.abhishek.security.securityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/auth")
public class AuthController {

    @Value("${deploy.environment}")
    private String deployedEnv ;

    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO response = userService.signUp(signUpDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDTO loginDTO, HttpServletResponse res) {
        LoginResponseDto response = authService.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken", response.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("development".equals(deployedEnv)); //This cookie can only be pass with https, for local host we disable it.
        res.addCookie(cookie);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken (HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        String refreshToken = Arrays.stream(cookies).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new SecurityException("No Refresh Token Found !"));
        return ResponseEntity.ok(authService.getNewAccessToken(refreshToken));
    }
}

/*
* How to avoid getting refresh Token Compromised ?
* We can minimize the compromise by securely storing the Refresh tokens in Same-Site, HTTP only and Secure Cookies
* Using HTTPS for transferring refresh token.
* */