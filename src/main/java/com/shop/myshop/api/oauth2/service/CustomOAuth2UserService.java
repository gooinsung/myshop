package com.shop.myshop.api.oauth2.service;

import com.shop.myshop.api.oauth2.attribute.OAuth2Attribute;
import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import com.shop.myshop.data.repository.UserRepository;
import com.shop.myshop.data.repository.UserRoleRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
        .getUserInfoEndpoint().getUserNameAttributeName();

    OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName,
        oAuth2User.getAttributes());

    Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

    String email = (String) memberAttribute.get("email");
    Optional<User> user = userRepository.findByUserIdAndProvider(email, registrationId);

    if (user.isEmpty()) {
      memberAttribute.put("exist", Boolean.FALSE);
      return new DefaultOAuth2User(
          Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
          memberAttribute, "email"
      );
    }

    List<UserRole> roleList = userRoleRepository.findAllByUser(user.get()).get();
    String role = roleList.stream()
        .map(UserRole::getRole)
        .map(Role::getRole)
        .collect(Collectors.joining(","));

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(role.split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    memberAttribute.put("exist", Boolean.TRUE);
    return new DefaultOAuth2User(
        authorities, memberAttribute, "email"
    );
  }
}
