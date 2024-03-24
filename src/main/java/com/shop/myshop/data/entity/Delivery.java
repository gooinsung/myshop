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
@Table(name = "MY_SHOP_DELIVERY")
public class Delivery extends BaseEntity{

  @Id
  @Column(name = "DELIVERY_SEQ")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long deliverySeq;

  @Size(max = 255, message = "배송기사 이름은 255 자를 넘을 수 없습니다.")
  @Column(name = "DELIVER_NAME", nullable = false)
  private String deliverName;

  @ColumnDefault("0")
  @Column(name = "DELIVERY_STATUS", nullable = false)
  private Integer deliveryStatus;

  @ManyToOne(optional = false)
  @JoinColumn(name = "ORDER_DETAIL_SEQ", nullable = false)
  private OrderDetail orderDetail;

  @ManyToOne(optional = false)
  @JoinColumn(name = "ORDER_SEQ", nullable = false)
  private Order order;

}
