package com.shop.myshop.data.dto;

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
public class OrderDetailDto extends BaseDto{
  private Long orderDetailSeq;
  private Integer goodsCount;
  private Long orderSeq;
  private Long goodsSeq;
  private Long goodsPriceSeq;

}
