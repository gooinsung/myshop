package com.shop.myshop.api.shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodsRequestDto {
    private long shopSeq;
    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String goodsName;
    @NotBlank(message = "상품 설명을 입력해 주세요.")
    private String goodsDescription;
    @NotNull(message = "상품 썸네일을 등록해 주세요.")
    private MultipartFile goodsThumbNail;
    private List<BigDecimal> goodsPriceList;
    private MultipartFile goodsImg;
}
