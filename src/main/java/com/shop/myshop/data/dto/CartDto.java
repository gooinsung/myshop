package com.shop.myshop.data.dto;

import com.shop.myshop.data.entity.Cart;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDto {
    private Long cartSeq;
    private Long goodsSeq;
    private Long goodsPriceSeq;
    private BigDecimal goodsPrice;
    private Long userSeq;

}
