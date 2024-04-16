package com.shop.myshop.api.file.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestFileDeleteRequestDto {
    private List<FileDeleteRequestDto> fileDeleteRequestDtoList;
}
