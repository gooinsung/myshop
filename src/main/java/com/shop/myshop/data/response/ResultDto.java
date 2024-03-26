package com.shop.myshop.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResultDto<T> {
  private HttpStatus status;
  private String resultMsg;
  private T resultData;

  public ResultDto(final HttpStatus status, final String resultMsg){
    this.status = status;
    this.resultMsg = resultMsg;
    this.resultData = null;
  }

  public static<T> ResultDto<T> res(final HttpStatus status, final String resultMsg){
    return res(status,resultMsg, null);
  }

  public static<T> ResultDto<T> res(final HttpStatus status, final String resultMsg, final T t){
    return ResultDto.<T>builder()
        .status(status)
        .resultMsg(resultMsg)
        .resultData(t)
        .build();
  }
}
