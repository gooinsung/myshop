package com.shop.myshop.api.file.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileDeleteRequestDto {
    private String filePath;
    private String bucket;
    private String originalFileName;
}
