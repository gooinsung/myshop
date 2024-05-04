package com.shop.myshop.api.cart.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartRequestDto {
    private Long goodsSeq;
    private Long goodsPriceSeq;
}
