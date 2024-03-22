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
public class OrderDto extends BaseDto{
  private Long orderNo;
  private BigDecimal orderTotalPrice;
  private Integer orderStatus;
  private Long shopSeq;
  private Long userSeq;

}
