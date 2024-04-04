package com.shop.myshop.api.admin.controller;

import com.shop.myshop.api.admin.service.AdminService;
import com.shop.myshop.data.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
  private final AdminService adminService;
  @GetMapping
  public String adminPage(){
    return "admin";
  }




}
