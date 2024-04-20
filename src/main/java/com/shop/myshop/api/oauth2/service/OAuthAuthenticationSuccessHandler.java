package com.shop.myshop.api.oauth2.service;

import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import com.shop.myshop.data.enums.MyShopUserRole;
import com.shop.myshop.data.repository.RoleRepository;
import com.shop.myshop.data.repository.UserRepository;
import com.shop.myshop.data.repository.UserRoleRepository;
import com.shop.myshop.data.response.ResultDto;
import com.shop.myshop.security.AuthProvider;
import com.shop.myshop.security.GenerateToken;
import com.shop.myshop.utils.JsonMessageUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthProvider authProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("===================== OAuthAuthenticationSuccessHandler =====================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String provider = oAuth2User.getAttribute("provider");

        boolean isExists = oAuth2User.getAttribute("exist");

        String role = oAuth2User.getAuthorities().stream()
                .findFirst()
                .orElseThrow(IllegalAccessError::new)
                .getAuthority();

        GenerateToken token;

        if (isExists) {
            User user = userRepository.findByUserIdAndProvider(email, provider).orElseThrow(() -> {
                throw new EntityNotFoundException("존재하지 않는 엔티티 입니다.");
            });
            token = authProvider.generateToken(user, role);
        } else {
            log.info("OAuth2 회원가입 진행");
            log.info("{} 회원 가입 진행 email : {}", provider, email);
            User user = User.builder()
                    .userId(email)
                    .provider(provider)
                    .build();
            User signUpUser = userRepository.saveAndFlush(user);

            Role roleUser = roleRepository.findById(MyShopUserRole.ROLE_USER.getRole()).get();
            UserRole userRole = UserRole
                    .builder()
                    .user(signUpUser)
                    .role(roleUser)
                    .build();

            userRoleRepository.saveAndFlush(userRole);
            token = authProvider.generateToken(signUpUser, MyShopUserRole.ROLE_USER.getRole());
        }
        response.addHeader("Content-Type", "application/json; charset=UTF-8");
        response.getWriter().write(Objects.requireNonNull(
                JsonMessageUtil.makeResponseJson(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), token)
                )));

    }
}
