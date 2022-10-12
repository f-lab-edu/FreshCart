package com.example.freshcart.global.exception;

import org.springframework.http.HttpStatus;

public class JsonProcessingException extends BaseException {

  private static final String message = "JSON 변환에 실패하였습니다";

  public JsonProcessingException() {
    super(HttpStatus.BAD_REQUEST, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
