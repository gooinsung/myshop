package com.shop.myshop.api.file.controller;

import com.shop.myshop.api.file.dto.FileDeleteRequestDto;
import com.shop.myshop.api.file.dto.FileRequestDto;
import com.shop.myshop.api.file.dto.FileResponseDto;
import com.shop.myshop.api.file.dto.RestFileDeleteResponseDto;
import com.shop.myshop.api.file.service.FileService;
import com.shop.myshop.data.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<ResultDto<FileResponseDto>> fileUpload(@ModelAttribute FileRequestDto requestDto) throws IOException {
        log.info("File Upload Request : {}", requestDto);
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), fileService.uploadFile(requestDto)));
    }

    @DeleteMapping
    public ResponseEntity<ResultDto<RestFileDeleteResponseDto>> fileListDelete(@RequestBody FileDeleteRequestDto requestDto) {
        log.info("File Delete Requset : {}", requestDto);
        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), fileService.deleteFileList(requestDto)));
    }
}
