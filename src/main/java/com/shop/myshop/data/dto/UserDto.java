package com.shop.myshop.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends BaseDto{
  private Long userSeq;
  private String userId;
  private String provider;
  private String userName;
  private String userNickname;

}
