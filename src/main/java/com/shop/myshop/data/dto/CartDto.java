package com.shop.myshop.data.dto;

import com.shop.myshop.data.entity.Cart;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDto {
    private Long cartSeq;
    private Long goodsSeq;
    private Long goodsPriceSeq;
    private Long userSeq;

}
