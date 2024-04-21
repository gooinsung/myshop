package com.shop.myshop.api.shop.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.myshop.data.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.shop.myshop.data.entity.QShop.shop;

@Repository
@RequiredArgsConstructor
public class ShopQueryRepository {
    private final JPAQueryFactory queryFactory;

    public ShopDto getShopDtoByShopAndUser(ShopDto shopDto) {
        return queryFactory
                .select(
                        Projections.fields(
                                ShopDto.class,
                                shop.shopSeq,
                                shop.shopName,
                                shop.shopDescription,
                                shop.user.userSeq
                        )
                ).from(shop)
                .where(shopDto.getShopSeq() == null ? null : shop.shopSeq.eq(shopDto.getShopSeq())
                        , shopDto.getUserSeq() == null ? null : shop.user.userSeq.eq(shopDto.getUserSeq())
                        , shop.isDeleted.eq(Boolean.FALSE)
                ).fetchOne();
    }
}
