package com.shop.myshop.exception.custom;

import com.shop.myshop.exception.CustomExceptionCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException{
  private final CustomExceptionCode code;

  BusinessLogicException(CustomExceptionCode code){
    super(code.getMessgae());
    this.code = code;
  }

}
