package com.shop.myshop.api.file.service;

import com.shop.myshop.api.file.dto.FileRequestDto;
import com.shop.myshop.api.file.dto.FileResponseDto;
import com.shop.myshop.api.file.enums.S3Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FileService {

    public FileResponseDto uploadFile(FileRequestDto fileRequestDto){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082";

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", fileRequestDto.getFile());
        parts.add("bucket", S3Bucket.MSA_MY_SHOP.getBucket());
        parts.add("message", fileRequestDto.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts,headers);

        ResponseEntity<FileResponseDto> response = restTemplate.postForEntity(url, requestEntity, FileResponseDto.class);
        return response.getBody();
    }
}
