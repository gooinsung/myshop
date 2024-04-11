package com.shop.myshop.api.file.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileRequestDto {
    private MultipartFile file;
    private String bucket;
    private String message;
    private String fileFolderPath;
}
