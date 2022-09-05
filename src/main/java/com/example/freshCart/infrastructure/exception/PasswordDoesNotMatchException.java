package com.example.freshCart.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class PasswordDoesNotMatchException extends BaseException {
  private static final String message = "아이디 또는 비밀번호가 일치하지 않습니다";

  public PasswordDoesNotMatchException() {
    super(HttpStatus.BAD_REQUEST, message);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
