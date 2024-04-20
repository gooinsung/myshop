package com.shop.myshop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.myshop.data.response.ResultDto;
import com.shop.myshop.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;

public class JsonMessageUtil {

    public static String makeErrorJson(String code, String message, String detail) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(code)
                .message(message)
                .detail(detail)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String makeResponseJson(ResultDto<?> resultDto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(ResponseEntity.ok().body(resultDto));
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
