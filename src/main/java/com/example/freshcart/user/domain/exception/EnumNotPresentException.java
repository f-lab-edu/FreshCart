package com.example.freshcart.user.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;


public class EnumNotPresentException extends BaseException {

  private static final String message = "지정된 타입이 제공되지 않았습니다";

  public EnumNotPresentException() {
    super(HttpStatus.BAD_REQUEST, message);
  }

  @Override
  public String getMessage() {
    return message;
  }

}
