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
@Table(name = "MY_SHOP_CART")
public class Cart extends BaseEntity {
    @Id
    @Column(name = "CART_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartSeq;

    @ManyToOne(optional = false)
    @JoinColumn(name = "GOODS_SEQ")
    private ShopGoods shopGoods;

    @ManyToOne(optional = false)
    @JoinColumn(name = "GOODS_PRICE_SEQ")
    private ShopGoodsPrice shopGoodsPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_SEQ", nullable = false)
    private User user;

    public void updatePrice(ShopGoodsPrice shopGoodsPrice) {
        this.shopGoodsPrice = shopGoodsPrice;
    }
}
