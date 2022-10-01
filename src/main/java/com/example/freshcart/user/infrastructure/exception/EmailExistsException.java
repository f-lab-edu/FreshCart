package com.example.freshcart.user.infrastructure.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * 회원 가입 중복 처리 시 이메일 고유 값 여부 체크.
 */

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
