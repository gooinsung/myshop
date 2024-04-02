package com.shop.myshop.api.payment.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestRequestDto {

    private String name;
    private String provider;
    private BigDecimal price;
    private String orderId;
}
