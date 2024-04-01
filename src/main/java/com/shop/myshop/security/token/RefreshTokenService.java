package com.shop.myshop.security.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveTokeninfo(String email, String accessToken, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(email, accessToken, refreshToken));
    }

    @Transactional
    public void removeRefreshToken(String accessToken) {
        RefreshToken token = refreshTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(IllegalArgumentException::new);
        refreshTokenRepository.delete(token);
    }
}
