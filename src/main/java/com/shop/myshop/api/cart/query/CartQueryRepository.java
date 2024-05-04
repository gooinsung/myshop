package com.shop.myshop.api.cart.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.myshop.data.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shop.myshop.data.entity.QCart.cart;

@Repository
@RequiredArgsConstructor
public class CartQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<CartDto> findAllByUserSeq(Long userSeq) {
        return queryFactory
                .select(
                        Projections.fields(
                                CartDto.class,
                                cart.cartSeq,
                                cart.shopGoods.goodsSeq,
                                cart.shopGoodsPrice.goodsPriceSeq,
                                cart.shopGoodsPrice.goodsPrice,
                                cart.user.userSeq
                        )
                ).from(cart)
                .where(
                        userSeq == null ? null : cart.user.userSeq.eq(userSeq)
                ).fetch();
    }

    public CartDto findByUserSeqAndGoodsSeq(CartDto cartDto) {
        return queryFactory
                .select(
                        Projections.fields(
                                CartDto.class,
                                cart.cartSeq,
                                cart.shopGoods.goodsSeq,
                                cart.shopGoodsPrice.goodsPriceSeq,
                                cart.shopGoodsPrice.goodsPrice,
                                cart.user.userSeq
                        )
                ).from(cart)
                .where(cartDto.getUserSeq() == null ? null : cart.user.userSeq.eq(cartDto.getUserSeq()),
                        cartDto.getGoodsSeq() == null ? null : cart.shopGoods.goodsSeq.eq(cartDto.getGoodsSeq())
                ).fetchOne();
    }
}
