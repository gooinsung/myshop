package com.shop.myshop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
@Table(name = "MY_SHOP_USER_SHOP")
public class Shop extends BaseEntity{
  @Id
  @Column(name = "SHOP_SEQ", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long shopSeq;

  @Size(max = 255, message = "샵 이름은 255 자를 넘을 수 없습니다.")
  @Column(name = "SHOP_NAME", nullable = false)
  private String shopName;

  @Column(name = "SHOP_DESCRIPTION")
  private String shopDescription;

  @ManyToOne(optional = false)
  @JoinColumn(name = "USER_SEQ", nullable = false)
  private User user;

}
