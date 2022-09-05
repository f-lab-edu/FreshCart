package com.example.freshCart.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class UnExpectedException extends BaseException {

  private static final String message = "예상치 못한 예외입니다";

  public UnExpectedException(Throwable cause) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
  }
}
