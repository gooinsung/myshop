package com.shop.myshop.data.dto;

import com.shop.myshop.data.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto{
  private Long userSeq;
  @Email
  private String userId;
  private String provider;
  private String userName;
  @NotBlank
  private String userPw;
  private String userNickname;

  public static User toEntity(UserDto userDto, PasswordEncoder encoder){
    return User.builder()
        .userId(userDto.getUserId())
        .provider(userDto.getProvider())
        .userPw(encoder.encode(userDto.userPw))
        .userName(userDto.getUserName())
        .userNickname(userDto.getUserNickname() != null ? userDto.getUserNickname() : null)
        .build();
  }

}
