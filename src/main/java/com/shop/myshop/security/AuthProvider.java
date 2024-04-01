package com.shop.myshop.security;

import com.shop.myshop.api.user.query.UserQueryRepository;
import com.shop.myshop.data.dto.UserDto;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.exception.custom.AuthTokenException;
import com.shop.myshop.security.token.RefreshTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthProvider implements InitializingBean {

    private final RefreshTokenService tokenService;
    private final UserQueryRepository userQueryRepository;

    private Key key;

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;
    @Value("${JWT_ACCESS_TOKEN_EXPIRED_TIME_MS}")
    private long accessTokenExpiredTimeMs;
    @Value("${JWT_REFRESH_TOKEN_EXPIRED_TIME_MS}")
    private long refreshTokenExpiredTimeMs;


    private static final String USER_ID = "userId";
    private static final String PROVIDER = "provider";
    private static final String AUTHENTICATION_KEY = "role";

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public GenerateToken generateToken(User user, String role) {
        String accessToken = this.generateAccessToken(user, role);
        String refreshToken = this.generateRefreshToken(user, role);
        tokenService.saveTokeninfo(user.getUserId(), accessToken, refreshToken);
        return GenerateToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String generateAccessToken(User user, String role) {

        Claims claims = Jwts.claims();
        claims.put(USER_ID, user.getUserId());
        claims.put(PROVIDER, user.getProvider());
        claims.put(AUTHENTICATION_KEY, role);

        long now = new Date().getTime();
        Date validity = new Date(now + this.accessTokenExpiredTimeMs);

        return Jwts.builder()
                .setSubject(user.getUserId())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String generateRefreshToken(User user, String role) {
        Claims claims = Jwts.claims();
        claims.put(USER_ID, user.getUserId());
        claims.put(PROVIDER, user.getProvider());
        claims.put(AUTHENTICATION_KEY, role);

        long now = new Date().getTime();
        Date validity = new Date(now + this.refreshTokenExpiredTimeMs);

        return Jwts.builder()
                .setSubject(user.getUserId())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();


        UserDto userDto = UserDto.builder()
                .userId((String) claims.get(USER_ID))
                .provider((String) claims.get(PROVIDER))
                .build();

        User principal = userQueryRepository.getUserDtoByIdAndProvider(userDto);

        if (principal == null)
            throw new AuthTokenException("존재하지 않는 계정입니다.");


        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHENTICATION_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("잘못된 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못됐습니다.");
        }
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
