package com.shop.myshop.api.file.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileDeleteResponseDto {
    private String originalFileName;
    private boolean isDeleted;
}
