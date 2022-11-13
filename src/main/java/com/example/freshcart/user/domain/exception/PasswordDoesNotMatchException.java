package com.example.freshcart.user.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * 로그인 시 이메일 존재 여부/패스워드 체크
 */

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
