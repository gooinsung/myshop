package com.shop.myshop.api.payment.controller;

import com.shop.myshop.api.payment.dto.TestRequestDto;
import com.shop.myshop.api.payment.dto.TestResponseDto;
import com.shop.myshop.data.response.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentRequestController {

    @PostMapping("/test")
    public ResponseEntity<ResultDto<Boolean>> testPaymentAPIRequest(@RequestBody TestRequestDto testRequestDto) {
        log.info("test API 요청 start");
        log.info("Request : {}", testRequestDto);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9080/payment/test";
        ResponseEntity<TestResponseDto> response = restTemplate.postForEntity(url, testRequestDto, TestResponseDto.class);

        if (response.getBody().getResult()) {
            log.info("Test API 성공!");
        } else {
            log.error("Test API 실패");
        }

        log.info("Response : {}", response);

        return ResponseEntity.ok().body(ResultDto.res(HttpStatus.OK, HttpStatus.OK.toString(), response.getBody().getResult()));
    }
}
