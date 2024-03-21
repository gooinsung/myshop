package com.shop.myshop.api.user.service;

import com.shop.myshop.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public boolean insertUser(){
    System.out.println(userRepository.findById(1L));
    return false;
  }
}
