package com.shop.myshop.api.cart.controller;

import com.shop.myshop.api.cart.dto.CartRequestDto;
import com.shop.myshop.api.cart.service.CartService;
import com.shop.myshop.data.dto.CartDto;
import com.shop.myshop.data.entity.User;
import com.shop.myshop.data.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ResultDto<List<CartDto>>> getCartList(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), cartService.getUserCartList(user)));
    }

    @PutMapping
    public ResponseEntity<ResultDto<Boolean>> registerCart(@RequestBody CartRequestDto requestDto,
                                                           @AuthenticationPrincipal User user) {
        CartDto cartDto = CartDto
                .builder()
                .userSeq(user.getUserSeq())
                .goodsSeq(requestDto.getGoodsSeq())
                .goodsPriceSeq(requestDto.getGoodsPriceSeq())
                .build();

        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), cartService.registerCart(cartDto)));
    }

    @DeleteMapping("/{cartSeq}")
    public ResponseEntity<ResultDto<Boolean>> deleteCart(@PathVariable Long cartSeq, @AuthenticationPrincipal User user) {

        CartDto cartDto =
                CartDto
                        .builder()
                        .cartSeq(cartSeq)
                        .userSeq(user.getUserSeq())
                        .build();

        cartService.deleteCart(cartDto);
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), Boolean.TRUE));
    }
}
