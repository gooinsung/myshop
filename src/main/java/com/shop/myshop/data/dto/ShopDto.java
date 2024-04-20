package com.shop.myshop.data.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ShopDto extends BaseDto {
    private Long shopSeq;
    @NotNull(message = "쇼핑몰 이름을 입력해주세요.")
    private String shopName;
    @Size(max = 1000, message = "설명은 1000 자를 넘을 수 없습니다.")
    private String shopDescription;
    private Long userSeq;

}
