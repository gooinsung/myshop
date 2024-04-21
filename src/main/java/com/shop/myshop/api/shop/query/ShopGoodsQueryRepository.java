package com.shop.myshop.api.shop.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.myshop.data.dto.ShopGoodsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.shop.myshop.data.entity.QShopGoods.shopGoods;

@Repository
@RequiredArgsConstructor
public class ShopGoodsQueryRepository {
    private final JPAQueryFactory queryFactory;

    public ShopGoodsDto getShopGoodsDtoByShopAndGoodsName(ShopGoodsDto shopGoodsDto) {
        return queryFactory
                .select(
                        Projections.fields(
                                ShopGoodsDto.class,
                                shopGoods.goodsSeq,
                                shopGoods.goodsName,
                                shopGoods.goodsDescription,
                                shopGoods.goodsImgUrl,
                                shopGoods.goodsImgFileName,
                                shopGoods.goodsThumbnail,
                                shopGoods.goodsThumbNailFileName,
                                shopGoods.shop.shopSeq
                        )
                )
                .from(shopGoods)
                .where(
                        shopGoodsDto.getGoodsName() == null ? null : shopGoods.goodsName.eq(shopGoodsDto.getGoodsName()),
                        shopGoodsDto.getShopSeq() == null ? null : shopGoods.shop.shopSeq.eq(shopGoodsDto.getShopSeq())
                ).fetchOne();
    }
}
