package com.shop.myshop.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;


@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MY_SHOP_GOODS_PRICE")
public class ShopGoodsPrice extends BaseEntity {

    @Id
    @Column(name = "GOODS_PRICE_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsPriceSeq;

    @Column(name = "GOODS_PRICE", nullable = false)
    private BigDecimal goods_price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "GOODS_SEQ", nullable = false)
    private ShopGoods shopGoods;

}
