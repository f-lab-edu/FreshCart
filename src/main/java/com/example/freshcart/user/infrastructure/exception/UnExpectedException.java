package com.example.freshcart.user.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 애플리케이션에서 정의한 것 이외의 예외.
 */

public class UnExpectedException extends BaseException {

  private static final String message = "예상치 못한 예외입니다";

  public UnExpectedException(Throwable cause) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
  }
}
