package com.example.freshcart.domain;

import java.time.LocalDateTime;
import lombok.Builder;

/**
 * 사용자에게 전달 될 에러 응답 형식 지정.
 * spring boot에서 기본적으로 제공해주는 Basic Error controller - DefaultErrorAttributes을 참고하여 변수 지정.
 * 변수가 많은 점을 고려하여, Builder 패턴 생성자 사용.
 */

@Builder
public class ErrorResult {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int status; // ex.404  - e.status.getValue()
  private final String error; // ex. Not Found -  e.status.getName()
  private final String message; // ex.404 NOT FOUND -  별도로 지정한 메시지.
  private final String path; // ex.users/login

  public ErrorResult(int status, String error, String message, String path) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }
}
