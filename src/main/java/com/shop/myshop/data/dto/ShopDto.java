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
public class ShopDto extends BaseDto{
  private Long shopSeq;
  private String shopName;
  private String shopDescription;
  private Long userSeq;

}
