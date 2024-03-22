package com.shop.myshop.api.shop.controller;

import com.shop.myshop.api.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {
  private final ShopService shopService;

}
