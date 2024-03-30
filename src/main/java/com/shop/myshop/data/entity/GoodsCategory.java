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
@Table(name = "MY_SHOP_GOODS_CATEGORY_MAP")
public class GoodsCategory extends BaseEntity{
  @Id
  @Column(name = "GOODS_CATEGORY_SEQ")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long goodsCategorySeq;

  @ManyToOne(optional = false)
  @JoinColumn(name = "GOODS_SEQ", nullable = false)
  private ShopGoods shopGoods;

  @ManyToOne(optional = false)
  @JoinColumn(name = "CATEGORY_SEQ", nullable = false)
  private Category category;
}
