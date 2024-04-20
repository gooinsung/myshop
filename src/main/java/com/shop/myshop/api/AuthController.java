package com.shop.myshop.api;

import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import com.shop.myshop.data.repository.UserRepository;
import com.shop.myshop.data.repository.UserRoleRepository;
import com.shop.myshop.data.response.ResultDto;
import com.shop.myshop.security.AuthProvider;
import com.shop.myshop.security.GenerateToken;
import com.shop.myshop.security.token.RefreshToken;
import com.shop.myshop.security.token.RefreshTokenRepository;
import com.shop.myshop.security.token.RefreshTokenService;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final AuthProvider authProvider;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @PostMapping("/token/logout")
    public ResponseEntity<ResultDto<Boolean>> logout(@RequestHeader("Authorization") String accessToken) {

        accessToken = getToken(accessToken);

        this.checkToken(accessToken);


        refreshTokenService.removeRefreshToken(accessToken);
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), Boolean.TRUE));

    }

    @PostMapping("/token/refresh")
    public ResponseEntity<ResultDto<GenerateToken>> refresh(@RequestHeader("Authorization") String accessToken) {

        accessToken = getToken(accessToken);

        this.checkToken(accessToken);

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccessToken(accessToken);

        if (refreshToken.isPresent() && authProvider.verifyToken(refreshToken.get().getRefreshToken())) {

            RefreshToken resultToken = refreshToken.get();

            User loginUser = userRepository.findByUserId(resultToken.getId()).get();

            if (loginUser == null)
                throw new EntityNotFoundException("존재하지 않는 계정입니다.");

            List<UserRole> roleList = userRoleRepository.findAllByUser(loginUser)
                    .orElseThrow(() -> new EntityNotFoundException("유저 역할이 존재하지 않습니다."));

            String role = roleList.stream()
                    .map(UserRole::getRole)
                    .map(Role::getRole)
                    .collect(Collectors.joining(","));

            GenerateToken newGenerateToken = authProvider.generateToken(loginUser, role);

            resultToken.updateAccessToken(newGenerateToken.getAccessToken());

            refreshTokenRepository.save(resultToken);

            return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), newGenerateToken));
        }

        return ResponseEntity.badRequest().body(ResultDto.res(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString()));
    }


    public String getToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    public void checkToken(String accessToken) {
        if (!StringUtils.hasText(accessToken))
            throw new JwtException("엑세스 토큰이 존재하지 않습니다.");
    }
}
