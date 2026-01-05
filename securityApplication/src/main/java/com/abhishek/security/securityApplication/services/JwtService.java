package com.abhishek.security.securityApplication.services;

import com.abhishek.security.securityApplication.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {
    //We will have two methods, one to create JWT token and another one to verify the JWT Token

    @Value("${jwt.secretKey}")
    private String jwtSecretKey ;


    /*
    * If the context of the exception is under the dispatcherServletContext then only our Global Exception Handler will handle the
    * exception but for this JWT exception
    * In the case of JWT exception, we are not in the dispatcher servlet context yet, we are in the filter/security context.
    * our code has not reached servlet dispatcher yet. So @RestControllerAdvice cannot wrap or catch this. As it will only
    * for the ServletDispatcher level exception.
    * To pass this exception to the servlet dispatcher we use this resolver. Resolver will help pass this error from one context
    * to another.
    * Check out the custom JWT filter to see how it is handled.
    * */
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver ;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8)) ;
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN", "USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*20))
                .signWith(getSecretKey())
                .compact();
    }

    //RefreshToken are generally very long loved 3-6 months. And they do not contain much data like roles, or
    //any other data. They are just there to recreate the accessToken by verification of these via refresh endpoint.
    public String generateRefreshToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60))
                .signWith(getSecretKey())
                .compact();
    }



    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject()) ;
    }
}
