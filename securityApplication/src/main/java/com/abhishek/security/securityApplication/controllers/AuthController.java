package com.abhishek.security.securityApplication.controllers;


import com.abhishek.security.securityApplication.dto.LoginDTO;
import com.abhishek.security.securityApplication.dto.SignUpDTO;
import com.abhishek.security.securityApplication.dto.UserDTO;
import com.abhishek.security.securityApplication.services.AuthService;
import com.abhishek.security.securityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO response = userService.signUp(signUpDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse res) {
        String token = authService.login(loginDTO);
        Cookie cookie = new Cookie("Token", token);
        cookie.setHttpOnly(true);
        res.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}