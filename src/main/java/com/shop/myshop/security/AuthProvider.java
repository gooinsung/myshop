package com.shop.myshop.security;

import com.shop.myshop.data.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements InitializingBean {
  private Key key;

  @Value("${JWT_SECRET_KEY}")
  private String secretKey;
  @Value("${JWT_EXPIRED_TIME_MS}")
  private long accessTokenExpiredTimeMs;

  private static final String USER_ID = "userId";
  private static final String AUTHENTICATION_KEY = "role";

  @Override
  public void afterPropertiesSet() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateAccessToken(User user, String role){

    Claims claims = Jwts.claims();
    claims.put("userId", user.getUserId());
    claims.put(USER_ID, user.getProvider());
    claims.put(AUTHENTICATION_KEY, role);

    long now = new Date().getTime();
    Date validITY = new Date(now + this.accessTokenExpiredTimeMs);

    return Jwts.builder()
        .setSubject(user.getUserId())
        .setClaims(claims)
        .signWith(key, SignatureAlgorithm.HS512)
        .setExpiration(validITY)
        .compact();
  }

  public Authentication getAuthentication(String token){
    Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();

    Object userId = claims.get(USER_ID);

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHENTICATION_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(userId, token, authorities);
  }

  public void validateToken(String token){
    try{
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    } catch (SecurityException | MalformedJwtException e){
      System.out.println("잘못된 JWT 서명 입니다.");
    } catch (ExpiredJwtException e){
      System.out.println("만료된 JWT 입니다.");
    } catch (UnsupportedJwtException e){
      System.out.println("지원되지 않는 JWT 입니다.");
    } catch (IllegalArgumentException e){
      System.out.println("JWT 토큰이 잘못됐습니다.");
    }
  }
}
