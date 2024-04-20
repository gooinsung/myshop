package com.shop.myshop.api.file.service;

import com.shop.myshop.api.file.dto.*;
import com.shop.myshop.api.file.enums.S3Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${FILE_SERVER_URL}")
    private String fileServerUrl;


    public FileResponseDto uploadFile(FileRequestDto fileRequestDto) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ByteArrayResource resource = new ByteArrayResource(fileRequestDto.getFile().getBytes()) {
            @Override
            public String getFilename() {
                return fileRequestDto.getFile().getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", resource);
        parts.add("bucket", S3Bucket.MSA_MY_SHOP.getBucket());
        parts.add("message", fileRequestDto.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, headers);

        ResponseEntity<FileResponseDto> response = restTemplate.postForEntity(fileServerUrl, requestEntity, FileResponseDto.class);
        return response.getBody();
    }

    public RestFileDeleteResponseDto deleteFileList(FileDeleteRequestDto requestDto) {
        RestTemplate restTemplate = new RestTemplate();

        requestDto.setBucket(S3Bucket.MSA_MY_SHOP.getBucket());

        List<FileDeleteRequestDto> fileDeleteRequsetDtoList = new ArrayList<>();
        fileDeleteRequsetDtoList.add(requestDto);

        RestFileDeleteRequestDto body = RestFileDeleteRequestDto.builder().fileDeleteRequestDtoList(fileDeleteRequsetDtoList).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RestFileDeleteRequestDto> requestEntity = new HttpEntity<>(body, headers);


        ResponseEntity<RestFileDeleteResponseDto> response = restTemplate.exchange(fileServerUrl, HttpMethod.DELETE, requestEntity, RestFileDeleteResponseDto.class);
        return response.getBody();
    }
}
