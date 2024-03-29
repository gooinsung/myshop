package com.shop.myshop.exception;

import static com.shop.myshop.exception.CustomExceptionCode.*;

import com.shop.myshop.exception.custom.BusinessLogicException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 예외 로깅
   *
   * @Param e, request
   */
  protected void logging(Exception e, HttpServletRequest request) {
    log.error(e.getClass().getSimpleName() + "occured");
    log.error("Request URI : {}", request.getRequestURI());
    log.error("Request Method : {}", request.getMethod());
  }

  private static String createValidationMessage(BindingResult bindingResult) {
    StringBuilder sb = new StringBuilder();
    boolean isFirst = true;

    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      if(!isFirst) {
        sb.append(", ");
      } else {
        isFirst = false;
      }
      sb.append("[");
      sb.append(fieldError.getField());
      sb.append("] ");
      sb.append(fieldError.getDefaultMessage());
    }

    return sb.toString();
  }

  /**
   * 비즈니스 로직 예외 처리
   */
  @ExceptionHandler(BusinessLogicException.class)
  protected ResponseEntity<ExceptionResponse> handleBusinessLogicException(BusinessLogicException e,
      HttpServletRequest request) {
    logging(e, request);
    return ResponseEntity
        .badRequest()
        .body(
            new ExceptionResponse(
                BUSINESS_LOGIC_EXCEPTION.getCode(),
                BUSINESS_LOGIC_EXCEPTION.getMessgae(),
                e.getMessage()
            )
        );
  }

  /**
   * method not supported 예외 처리
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
    logging(e, request);
    return ResponseEntity
        .badRequest()
        .body(
            new ExceptionResponse(
                HTTP_METHOD_NOT_SUPPORTED.getCode(),
                HTTP_METHOD_NOT_SUPPORTED.getMessgae(),
                e.getMessage()
            )
        );
  }

  /**
   * 404 예외 처리
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  protected ResponseEntity<ExceptionResponse> handleNoHandlerFoundException(
      NoHandlerFoundException e, HttpServletRequest request) {
    log.error("NoHandlerFoundException occured");
    log.error("Request URL : {}", request.getRequestURI());
    return ResponseEntity
        .badRequest()
        .body(
            new ExceptionResponse(
                NOT_FOUND.getCode(),
                NOT_FOUND.getMessgae(),
                e.getMessage()
            )
        );
  }

  /**
   * entity not found 예외처리
   */
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<ExceptionResponse> handleExceptionNotFoundException(
      EntityNotFoundException e, HttpServletRequest request) {
    log.error("EntityNotFoundException occured");
    return ResponseEntity
        .badRequest()
        .body(
            new ExceptionResponse(
                ENTITY_NOT_FOUND.getCode(),
                ENTITY_NOT_FOUND.getMessgae(),
                e.getMessage()
            )
        );
  }

  /**
   * Runtimeexception 예외처리
   */
/*  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e,
      HttpServletRequest request) {
    logging(e, request);
    return ResponseEntity
        .badRequest()
        .body(
            new ExceptionResponse(
                RUNTIME_EXCEPTION.getCode(),
                RUNTIME_EXCEPTION.getMessgae(),
                e.getMessage()
            )
        );
  }*/

  /**
   * Validation 예외처리
   */
  @ExceptionHandler({BindException.class})
  protected ResponseEntity<ExceptionResponse> handleBindException(BindException e,
      HttpServletRequest request) {
    logging(e, request);
    return ResponseEntity
        .badRequest()
        .body(
            new ExceptionResponse(
                VALIDATION_PARAMETER_EXCEPTION.getCode(),
                VALIDATION_PARAMETER_EXCEPTION.getMessgae(),
                createValidationMessage(e.getBindingResult())
            )
        );
  }


}
