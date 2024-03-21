package com.shop.myshop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

  @Id
  @Column(name = "USER_SEQ", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userSeq;

  @Size(max = 255)
  @Column(name = "USER_ID", nullable = false)
  private String userId;

  @Size(max=255)
  @Column(name = "PROVIDER", nullable = false)
  private String provider;

  @Size(max=255)
  @Column(name = "USER_NAME", nullable = false)
  private String userName;

  @Size(max = 255)
  @Column(name = "USER_PW", nullable = true)
  private String userPw;

  @Size(max = 255)
  @Column(name = "USER_NICKNAME", nullable = true)
  private String userNickname;

}
