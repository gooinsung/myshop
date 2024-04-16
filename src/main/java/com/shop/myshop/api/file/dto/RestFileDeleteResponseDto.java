package com.shop.myshop.api.file.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestFileDeleteResponseDto {
    private List<FileDeleteResponseDto> fileDeleteResponseDtoList;
}
