package com.example.freshCart.infrastructure.exception;

public class UserNotExistsException extends RuntimeException {

  private final String message = "존재하지 않는 유저입니다";

  public UserNotExistsException(String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
