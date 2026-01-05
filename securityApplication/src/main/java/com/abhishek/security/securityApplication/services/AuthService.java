package com.abhishek.security.securityApplication.services;

import com.abhishek.security.securityApplication.dto.LoginDTO;
import com.abhishek.security.securityApplication.dto.LoginResponseDto;
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
    private final UserService userService ;
    private final SessionService sessionService ;

    public LoginResponseDto login(LoginDTO loginDTO) {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user, refreshToken);
        return new LoginResponseDto(user.getId(), accessToken, refreshToken) ;
    }

    //Uses refresh token to generate accessToken.
    public LoginResponseDto getNewAccessToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(userId, accessToken, refreshToken);
    }

}
