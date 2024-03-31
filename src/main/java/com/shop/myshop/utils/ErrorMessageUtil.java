package com.shop.myshop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.myshop.exception.ExceptionResponse;

public class ErrorMessageUtil {

  public static String makeErrorJson(String code, String message, String detail) {
    ExceptionResponse response = ExceptionResponse.builder()
        .code(code)
        .message(message)
        .detail(detail)
        .build();
    ObjectMapper mapper = new ObjectMapper();
    try{
      return mapper.writeValueAsString(response);
    }catch (JsonProcessingException e){
      return null;
    }
  }
}
