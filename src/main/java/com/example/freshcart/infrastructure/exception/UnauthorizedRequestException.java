package com.example.freshcart.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 인증이 필요하지만, 인증 없이 요청했을 경우 예외.
 */

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
