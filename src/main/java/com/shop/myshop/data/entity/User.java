package com.shop.myshop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MY_SHOP_USER")
public class User extends BaseEntity {

  @Id
  @Column(name = "USER_SEQ", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userSeq;

  @Email(message = "이메일 형식을 확인하세요.")
  @Size(max = 255, message = "ID 는 255 자를 넘을 수 없습니다.")
  @Column(name = "USER_ID", nullable = false)
  private String userId;

  @Size(max=255, message = "제공자를 입력하세요")
  @Column(name = "PROVIDER", nullable = false)
  private String provider;

  @Size(max=255, message = "이름은 255 자를 넘을 수 없습니다.")
  @Column(name = "USER_NAME", nullable = false)
  private String userName;

  @Size(max = 255, message = "PW 는 255 자를 넘을 수 없습니다.")
  @Column(name = "USER_PW")
  private String userPw;

  @Size(max = 255, message = "닉네임은 255 자를 넘을 수 없습니다.")
  @Column(name = "USER_NICKNAME")
  private String userNickname;

  public void passwordEncoding(PasswordEncoder encoder){
    this.userPw = encoder.encode(this.userPw);
  }
}
