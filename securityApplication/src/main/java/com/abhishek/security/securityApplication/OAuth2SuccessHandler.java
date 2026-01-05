package com.abhishek.security.securityApplication;

import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.services.JwtService;
import com.abhishek.security.securityApplication.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Value("${deploy.environment}")
    private String deployedEnv ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication ;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal() ;
        log.info(oAuth2User.getAttribute("email"));
        String email = oAuth2User.getAttribute("email");
        User user = userService.getUserByEmail(email);
        //User does not exists
        if(user == null){
            User newUser = User.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .build();
            user = userService.save(newUser);
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure("development".equals(deployedEnv)); //This cookie can only be pass with https, for local host we disable it.
        response.addCookie(cookie);
        //To send the AccessToken to the FE client for making API calls with accesstoken for the subsequest request
        String frontendUrl = "http://localhost:8081/home.html?token="+accessToken ;
        getRedirectStrategy().sendRedirect(request, response, frontendUrl);

        //Another way to redirect is :
        //response.sendRedirect(frontendUrl);

    }
}
