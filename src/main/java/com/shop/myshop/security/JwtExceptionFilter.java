package com.shop.myshop.security;

import static com.shop.myshop.exception.CustomExceptionCode.AUTHENTICATION_FAILED;
import static com.shop.myshop.exception.CustomExceptionCode.UNAUTHORIZED;

import com.shop.myshop.exception.custom.AuthTokenException;
import com.shop.myshop.utils.ErrorMessageUtil;
import com.shop.myshop.utils.ExceptionUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
      ExceptionUtil.errorLogging(e, request);
      response.addHeader("Content-Type", "application/json; charset=UTF-8");
      response.getWriter().write(Objects.requireNonNull(
          ErrorMessageUtil.makeErrorJson(AUTHENTICATION_FAILED.getCode(),
              AUTHENTICATION_FAILED.getMessgae(), e.getMessage())));
    } catch (SecurityException | MalformedJwtException | SignatureException e) {
      ExceptionUtil.errorLogging(e, request);
      response.addHeader("Content-Type", "application/json; charset=UTF-8");
      response.getWriter().write(Objects.requireNonNull(
          ErrorMessageUtil.makeErrorJson(UNAUTHORIZED.getCode(),
              UNAUTHORIZED.getMessgae(),
              e.getMessage())
      ));
    } catch (ExpiredJwtException e) {
      ExceptionUtil.errorLogging(e, request);
      response.addHeader("Content-Type", "application/json; charset=UTF-8");
      response.getWriter().write(Objects.requireNonNull(
          ErrorMessageUtil.makeErrorJson(UNAUTHORIZED.getCode(),
              UNAUTHORIZED.getMessgae(),
              e.getMessage())
      ));
    } catch (UnsupportedJwtException e) {
      ExceptionUtil.errorLogging(e, request);
      response.addHeader("Content-Type", "application/json; charset=UTF-8");
      response.getWriter().write(Objects.requireNonNull(
          ErrorMessageUtil.makeErrorJson(UNAUTHORIZED.getCode(),
              UNAUTHORIZED.getMessgae(),
              e.getMessage())
      ));
    } catch (IllegalArgumentException e) {
      ExceptionUtil.errorLogging(e, request);
      response.addHeader("Content-Type", "application/json; charset=UTF-8");
      response.getWriter().write(Objects.requireNonNull(
          ErrorMessageUtil.makeErrorJson(UNAUTHORIZED.getCode(),
              UNAUTHORIZED.getMessgae(),
              e.getMessage())
      ));
    }
  }

}
