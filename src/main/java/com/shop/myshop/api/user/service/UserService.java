package com.shop.myshop.api.user.service;

import com.shop.myshop.api.user.query.UserQueryRepository;
import com.shop.myshop.data.dto.UserDto;
import com.shop.myshop.data.enums.Provider;
import com.shop.myshop.data.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final UserQueryRepository userQueryRepository;
  private final PasswordEncoder passwordEncoder;


  /**
   * 회원 가입
   * @Param userDto
   * */
  public void signUp(UserDto userDto){

    if(userDto.getProvider() == null)
      userDto.setProvider(Provider.SYSTEM.getProvider());

    if(userQueryRepository.getUserDtoByIdAndProvider(userDto) != null)
      throw new EntityNotFoundException("이미 있는 계정입니다.");

    userRepository.saveAndFlush(UserDto.toEntity(userDto, passwordEncoder));
  }

  
}
