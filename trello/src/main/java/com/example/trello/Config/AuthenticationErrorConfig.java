package com.example.trello.Config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Response.BaseResponse.ErrorResponses;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationErrorConfig implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        BaseResponse<String> error = BaseResponse.<String>builder().error(
                ErrorResponses.builder().code(HttpStatus.UNAUTHORIZED.value()).message("UNAUTHORIZED").build()).success(false)
                .build();
        log.error("Unauthorized access detected!");
        log.error("Timestamp: {}", LocalDateTime.now());
        log.error("Request URL: {}", request.getRequestURI());
        log.error("HTTP Method: {}", request.getMethod());
        log.error("Remote Address: {}", request.getRemoteAddr());
        log.error("Exception Message: {}", authException.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter()
                .write(objectMapper.writeValueAsString(error));
    }
}