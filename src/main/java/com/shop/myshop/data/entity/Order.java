package com.shop.myshop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MY_SHOP_ORDER")
public class Order extends BaseEntity{

  @Id
  @Column(name = "ORDER_SEQ")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderSeq;

  @Column(name = "ORDER_TOTAL_PRICE", nullable = false)
  private BigDecimal orderTotalPrice;

  @ColumnDefault("0")
  @Column(name = "ORDER_STATUS", nullable = false)
  private Integer orderStatus;

  @ManyToOne
  @JoinColumn(name = "SHOP_SEQ", nullable = false)
  private Shop shop;

  @ManyToOne
  @JoinColumn(name = "USER_SEQ", nullable = false)
  private User user;

}
