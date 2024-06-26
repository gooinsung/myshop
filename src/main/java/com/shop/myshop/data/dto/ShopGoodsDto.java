package com.shop.myshop.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShopGoodsDto extends BaseDto {
    private Long goodsSeq;
    private String goodsName;
    private String goodsDescription;
    private String goodsImgUrl;
    private String goodsImgFileName;
    private String goodsThumbnail;
    private String goodsThumbNailFileName;
    private Long shopSeq;
    private List<ShopGoodsPriceDto> goodsPriceDtoList;
}
