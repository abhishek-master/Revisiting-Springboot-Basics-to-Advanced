package com.abhishek.security.securityApplication.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Service
@RequiredArgsConstructor
public class RequestResponseLoggerFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        try{
            filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        }finally{
            long duration = System.currentTimeMillis() - startTime ;

            logRequest(contentCachingRequestWrapper);
            logResponse(contentCachingResponseWrapper, duration);
            contentCachingResponseWrapper.copyBodyToResponse(); //Check the docs _/\_
        }


    }


    private void logRequest(ContentCachingRequestWrapper request) {
        StringBuilder requestLog = new StringBuilder();
        requestLog.append("\n=== Incoming Request ===\n");
        requestLog.append("Method: ").append(request.getMethod()).append("\n");
        requestLog.append("URI: ").append(request.getRequestURI());

        if (request.getQueryString() != null) {
            requestLog.append("?").append(request.getQueryString());
        }
        requestLog.append("\n");

        // Log headers
        requestLog.append("Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            // Mask sensitive headers
            if (isSensitiveHeader(headerName)) {
                headerValue = "***MASKED***";
            }
            requestLog.append("  ").append(headerName).append(": ").append(headerValue).append("\n");
        }

        // Log request body
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            String body = new String(content, StandardCharsets.UTF_8);
            requestLog.append("Body: ").append(body).append("\n");
        }

        logger.info(requestLog.toString());
    }


    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        StringBuilder responseLog = new StringBuilder();
        responseLog.append("\n=== Outgoing Response ===\n");
        responseLog.append("Status: ").append(response.getStatus()).append("\n");
        responseLog.append("Duration: ").append(duration).append("ms\n");

        // Log response headers
        responseLog.append("Headers:\n");
        for (String headerName : response.getHeaderNames()) {
            String headerValue = response.getHeader(headerName);
            responseLog.append("  ").append(headerName).append(": ").append(headerValue).append("\n");
        }

        // Log response body
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            String body = new String(content, StandardCharsets.UTF_8);
            responseLog.append("Body: ").append(body).append("\n");
        }

        logger.info(responseLog.toString());
    }

    private boolean isSensitiveHeader(String headerName) {
        String lowerCaseHeader = headerName.toLowerCase();
        return lowerCaseHeader.contains("authorization") ||
                lowerCaseHeader.contains("password") ||
                lowerCaseHeader.contains("token") ||
                lowerCaseHeader.contains("cookie");
    }
}
