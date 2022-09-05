package com.example.freshCart.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends BaseException {

  private static final String message = "이미 존재하는 이메일입니다";

  public EmailExistsException() {
    super(HttpStatus.BAD_REQUEST, message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
