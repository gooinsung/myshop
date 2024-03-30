package com.shop.myshop.security.service;

import com.shop.myshop.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

  public void saveTokenInfo(User user, String accessToken, String refreshToken){

  }

}
