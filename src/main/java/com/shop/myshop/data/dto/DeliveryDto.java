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
public class DeliveryDto extends BaseDto{
  private Long deliverySeq;
  private String deliverName;
  private Integer deliveryStatus;
  private Long orderDetailSeq;
  private Long orderSeq;

}
