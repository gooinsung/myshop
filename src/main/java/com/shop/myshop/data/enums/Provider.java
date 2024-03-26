package com.shop.myshop.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Provider {
  SYSTEM("system"), KAKAO("kakao"), GOOGLE("google"), NAVER("naver");

  private final String provider;
}
