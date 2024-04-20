package com.shop.myshop.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String goodsThumbnail;
    private Long shopSeq;

}
