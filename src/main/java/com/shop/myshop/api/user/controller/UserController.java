package com.shop.myshop.api.user.controller;

import com.shop.myshop.api.admin.service.AdminService;
import com.shop.myshop.api.user.service.UserService;
import com.shop.myshop.data.dto.UserDto;
import com.shop.myshop.data.response.ResultDto;
import com.shop.myshop.response.CustomResponseCode;
import com.shop.myshop.security.GenerateToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AdminService adminService;


    @PostMapping("/sign-up")
    public ResponseEntity<ResultDto<Boolean>> signUp(@RequestBody @Validated UserDto userDto) {
        log.info("회원 가입 요청 userDto : {}", userDto);
        userService.signUp(userDto);
        return ResponseEntity.ok()
                .body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), Boolean.TRUE));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResultDto<GenerateToken>> signin(@RequestBody @Validated UserDto userDto) {
        log.info("로그인 요청 userDto : {}", userDto);
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(),
                userService.login(userDto)));
    }

    @PostMapping("/admin/{userSeq}")
    public ResponseEntity<ResultDto<CustomResponseCode>> registAdmin(@PathVariable(required = true) @Validated Long userSeq) {
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), adminService.registerAdmin(userSeq)));
    }


}
