package com.shop.myshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * 인증 실패시 호출될 Entry point
 * */
@Slf4j
@Component
public class ServiceAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private ObjectMapper objectMapper;
  public ServiceAuthenticationEntryPoint(final ObjectMapper objectMapper){
    this.objectMapper = objectMapper;
  }
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    Map<String, Object> commenceResponse = new HashMap<>();
    commenceResponse.put("code", "401 ERROR");
    commenceResponse.put("message", "API Access was detected from unauthenticated user.");
    objectMapper.writeValue(response.getWriter(), commenceResponse);
  }
}
