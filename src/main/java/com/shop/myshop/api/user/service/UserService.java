package com.shop.myshop.api.user.service;

import com.shop.myshop.data.dto.UserDto;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.enums.Provider;
import com.shop.myshop.data.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void signUp(UserDto userDto){

    if(userRepository.findByUserId(userDto.getUserId()).isPresent()){
      throw new EntityNotFoundException("이미 존재하는 아이디 입니다.");
    }

    userDto.setProvider(Provider.SYSTEM.getProvider());
    User user = UserDto.toEntity(userDto, passwordEncoder);
    userRepository.saveAndFlush(user);
  }
}
