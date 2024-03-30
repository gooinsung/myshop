package com.shop.myshop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MY_SHOP_USER_ROLE_MAP")
public class UserRole extends BaseEntity{
  @Id
  @Column(name = "USER_ROLE_SEQ")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userRoleSeq;

  @ManyToOne
  @JoinColumn(name = "USER_SEQ")
  private User user;

  @ManyToOne
  @JoinColumn(name = "ROLE_SEQ")
  private Role role;
}
