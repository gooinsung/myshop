package com.shop.myshop.api.user.controller;

import com.shop.myshop.api.user.service.UserService;
import com.shop.myshop.data.dto.UserDto;
import com.shop.myshop.data.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;


  @PostMapping("/sign-up")
  public ResponseEntity<ResultDto<Boolean>> signUp(@RequestBody @Validated UserDto userDto){
    log.info("회원 가입 요청 userDto : {}", userDto);
    if(1==1){
      throw new RuntimeException();

    }
    userService.signUp(userDto);
    return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), Boolean.TRUE));
  }


}
