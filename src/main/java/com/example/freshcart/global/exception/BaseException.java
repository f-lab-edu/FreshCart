package com.example.freshcart.global.exception;

import org.springframework.http.HttpStatus;

/**
 * BaseException: Exception 의 공통 기능 정의 Base Exception 을 상속 받아서 핸들러에서 공통으로 처리할 수 있도록 한다. 추후 로깅을 할 경우,
 * logLevel도 설정한다.
 */
public class BaseException extends RuntimeException {

  private final HttpStatus status;


  public BaseException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public BaseException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
