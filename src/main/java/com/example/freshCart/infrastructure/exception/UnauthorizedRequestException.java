package com.example.freshCart.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedRequestException extends BaseException {

  private static final String message = "인증되지 않은 요청입니다";

  public UnauthorizedRequestException() {
    super(HttpStatus.UNAUTHORIZED, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
