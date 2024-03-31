package com.shop.myshop.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtil {
  public static void errorLogging(Exception e, HttpServletRequest request){
    log.error(e.getClass().getSimpleName() + " occured");
    log.error("Request URI : {}", request.getRequestURI());
    log.error("Request Method : {}", request.getMethod());
  }

}
