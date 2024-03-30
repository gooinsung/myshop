package com.shop.myshop.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.myshop.exception.ExceptionResponse;
import com.shop.myshop.exception.custom.AuthTokenException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.shop.myshop.exception.CustomExceptionCode.*;
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (AuthTokenException e) {
      response.addHeader("Content-Type", "application/json; charset=UTF-8");
      response.getWriter().write(makeErrorJson(AUTHENTICATION_FAILED.getCode(), AUTHENTICATION_FAILED.getMessgae(), e.getMessage()));
    }
  }

  public static String makeErrorJson(String code, String message, String detail) {
    ExceptionResponse response = ExceptionResponse.builder()
        .code(code)
        .message(message)
        .detail(detail)
        .build();
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
