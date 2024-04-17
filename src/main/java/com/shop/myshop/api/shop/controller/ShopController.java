package com.shop.myshop.api.shop.controller;

import com.shop.myshop.api.shop.service.ShopService;
import com.shop.myshop.data.dto.ShopDto;
import com.shop.myshop.data.entity.Shop;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/{shopSeq}")
    public ResponseEntity<ResultDto<List<Shop>>> mySHop(@PathVariable Long shopSeq) {
        shopService.test();
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString()));
    }

    @PostMapping
    public ResponseEntity<ResultDto<ShopDto>> createShop(@RequestBody @Validated ShopDto shopDto, @AuthenticationPrincipal User user) {
        shopDto.setUserSeq(user.getUserSeq());
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), shopService.createShop(shopDto)));
    }
}
