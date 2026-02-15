package com.abhishek.security.securityApplication.filters;

import com.abhishek.security.securityApplication.entities.User;
import com.abhishek.security.securityApplication.services.JwtService;
import com.abhishek.security.securityApplication.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

//Adding a new filter in the Security filter chain of Spring Security [Step 1]
@Service
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver ;

    private final JwtService jwtService ;
    private final UserService userService ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Getting the token in the Authorization Header for verification

        try {
            final String requestTokenHeader = request.getHeader("Authorization");

            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response); // to make sure rest filters process in the filter chain
                return;
            }

            String token = requestTokenHeader.split("Bearer ")[1];

            Long userId = jwtService.getUserIdFromToken(token); //Verifies the token and gets the User detail
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.getUserById(userId);
                /*
                Now you can see we are adding the authorities, and it will be stored in the Security Context. And
                these authorities will be used to filter which routes and actions a user can access/perform.
                */
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                /*
                before we add the authentication token, we can add certain authentication details, like in this case we can pass the
                web detail (See the below line), it contains the IP address, source and other things. Can be helpful, say in case for rate limiting.
                * */
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            // to make sure rest filters process in the filter chain
            filterChain.doFilter(request, response);
        }catch(Exception ex){
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }

        //here we can do something with the response, once a request goes through the filter chain it comes back as well.

    }
}
