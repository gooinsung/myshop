package com.shop.myshop.api.shop.controller;

import com.shop.myshop.api.shop.service.ShopService;
import com.shop.myshop.data.entity.Shop;
import com.shop.myshop.data.response.ResultDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
  private final ShopService shopService;

  @GetMapping("/{shopSeq}")
  public ResponseEntity<ResultDto<List<Shop>>> mySHop(@PathVariable Long shopSeq){
    return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString()));
  }
}
