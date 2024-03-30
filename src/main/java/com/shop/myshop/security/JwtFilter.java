package com.shop.myshop.security;

import com.shop.myshop.exception.custom.AuthTokenException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private final AuthProvider authProvider;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("===================== AuthFilter =====================");
    String accessToken = this.resolveToken(request);

    try {
      if (StringUtils.hasText(accessToken)) {
        authProvider.validateToken(accessToken);
        Authentication authentication = authProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
      } else {
        throw new AuthTokenException("유효한 JWT 토큰이 없습니다.");
      }
    } catch (AuthTokenException e){
      log.error("토큰 없음");
    }

    filterChain.doFilter(request, response);
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(0, 7);
    }

    return null;
  }
}
