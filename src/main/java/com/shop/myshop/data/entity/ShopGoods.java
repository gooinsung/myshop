package com.shop.myshop.data.entity;

import com.shop.myshop.data.dto.ShopGoodsDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MY_SHOP_GOODS",
        indexes = {
                @Index(name = "idx_SHOP_GOODS_NAME", columnList = "SHOP_SEQ, GOODS_NAME", unique = true)
        })
public class ShopGoods extends BaseEntity {
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

    @Column(name = "GOODS_IMG_FILE_NAME")
    private String goodsImgFileName;

    @Size(max = 255, message = "상품 썸네일 주소가 너무 깁니다.")
    @Column(name = "GOODS_THUMBNAIL", nullable = false)
    private String goodsThumbnail;

    @Column(name = "GOODS_THUMBNAIL_FILE_NAME", nullable = false)
    private String goodsThumbNailFileName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SHOP_SEQ", nullable = false)
    private Shop shop;

    public ShopGoodsDto of() {
        return ShopGoodsDto
                .builder()
                .shopSeq(this.shop.getShopSeq())
                .goodsName(this.goodsName)
                .goodsDescription(this.goodsDescription)
                .goodsImgUrl(this.goodsImgUrl)
                .goodsImgFileName(this.goodsImgFileName)
                .goodsThumbnail(this.goodsThumbnail)
                .goodsThumbNailFileName(this.goodsThumbNailFileName)
                .build();
    }

}
