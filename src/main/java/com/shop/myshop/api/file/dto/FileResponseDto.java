package com.shop.myshop.api.file.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileResponseDto {
    private Boolean result;
    private String originalFileName;
    private String fileUrl;
    private String message;
}
