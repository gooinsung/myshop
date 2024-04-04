package com.shop.myshop.api.oauth2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OAuthLoginController {

  @GetMapping("/login")
  public String oAuthLoginPage(){
    return "login";
  }
}
