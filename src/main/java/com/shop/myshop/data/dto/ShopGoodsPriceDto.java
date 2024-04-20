package com.shop.myshop.data.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShopGoodsPriceDto extends BaseDto {
    private Long goodsPriceSeq;
    private BigDecimal goodsPrice;
    private Long goodsSeq;

}
