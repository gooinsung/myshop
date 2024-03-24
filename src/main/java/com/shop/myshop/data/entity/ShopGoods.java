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
@Table(name = "MY_SHOP_GOODS")
public class ShopGoods extends BaseEntity{
  @Id
  @Column(name = "GOODS_SEQ", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long goodsSeq;

  @Size(max = 255, message = "상품 이름은 255 자를 넘을 수 없습니다.")
  @Column(name = "GOODS_NAME", nullable = false)
  private String goodsName;

  @Column(name = "GOODS_DESCRIPTION")
  private String goodsDescription;

  @Size(max = 255, message = "상품 이미지 url 주소가 너무 깁니다.")
  @Column(name = "GOODS_IMG_URL")
  private String goodsImgUrl;

  @Size(max = 255, message = "상품 썸네일 주소가 너무 깁니다.")
  @Column(name = "GOODS_THUMBNAIL")
  private String goodsThumbnail;

  @ManyToOne(optional = false)
  @JoinColumn(name = "SHOP_SEQ", nullable = false)
  private Shop shop;


}
