package com.shop.myshop.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MY_SHOP_ORDER_DETAIL")
public class OrderDetail extends BaseEntity {

    @Id
    @Column(name = "ORDER_DETAIL_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailSeq;

    @Column(name = "GOODS_COUNT", nullable = false)
    private Integer goodsCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_SEQ", nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "GOODS_SEQ", nullable = false)
    private ShopGoods shopGoods;

    @ManyToOne(optional = false)
    @JoinColumn(name = "GOODS_PRICE_SEQ", nullable = false)
    private ShopGoodsPrice shopGoodsPrice;

}
