package com.shop.myshop.api.user.service;

import com.shop.myshop.api.user.query.UserQueryRepository;
import com.shop.myshop.data.dto.UserDto;
import com.shop.myshop.data.entity.Role;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.entity.UserRole;
import com.shop.myshop.data.enums.MyShopUserRole;
import com.shop.myshop.data.enums.Provider;
import com.shop.myshop.data.repository.RoleRepository;
import com.shop.myshop.data.repository.UserRepository;
import com.shop.myshop.data.repository.UserRoleRepository;
import com.shop.myshop.exception.CustomExceptionCode;
import com.shop.myshop.security.AuthProvider;
import com.shop.myshop.security.GenerateToken;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserRoleRepository userRoleRepository;
  private final UserQueryRepository userQueryRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthProvider authProvider;


  /**
   * 회원 가입
   *
   * @Param userDto
   */
  @Transactional
  public void signUp(UserDto userDto) {

    if (userDto.getProvider() == null) {
      userDto.setProvider(Provider.SYSTEM.getProvider());
    }

    if (userQueryRepository.getUserDtoByIdAndProvider(userDto) != null) {
      throw new EntityNotFoundException("이미 있는 계정입니다.");
    }

    // 회원 가입
    User user = UserDto.toEntity(userDto);
    user.passwordEncoding(passwordEncoder);
    User signUpUser = userRepository.saveAndFlush(user);

    // User Role 삽입
    Role role = roleRepository.findById(MyShopUserRole.ROLE_USER.getRole())
        .orElseThrow(() -> new EntityNotFoundException(
            "존재하지 않는 role 입니다. 개발자에게 문의하세요."));

    UserRole userRole = UserRole.builder()
        .role(role)
        .user(signUpUser)
        .build();

    userRoleRepository.save(userRole);
  }

  /**
   * 로그인
   *
   * @param userDto
   */
  public GenerateToken login(UserDto userDto) {
    if (userDto.getProvider() == null) {
      userDto.setProvider(Provider.SYSTEM.getProvider());
    }

    User loginUser = userQueryRepository.getUserDtoByIdAndProvider(userDto);


    if (loginUser == null) {
      throw new EntityNotFoundException("존재하지 않는 계정입니다.");
    }

    if(!passwordEncoder.matches(userDto.getUserPw(), loginUser.getUserPw())){
      throw new IllegalArgumentException(CustomExceptionCode.WRONG_PW.getMessgae());
    }

    List<UserRole> roleList = userRoleRepository.findAllByUser(loginUser)
        .orElseThrow(()-> new EntityNotFoundException("유저 역할이 존재하지 않습니다."));

    String role = roleList.stream()
        .map(UserRole::getRole)
        .map(Role::getRole)
        .collect(Collectors.joining(","));

    return authProvider.generateToken(loginUser, role);
  }


}
