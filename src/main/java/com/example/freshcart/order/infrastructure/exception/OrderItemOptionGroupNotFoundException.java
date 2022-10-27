package com.example.freshcart.order.infrastructure.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OrderItemOptionGroupNotFoundException extends BaseException {
  private static final String message = "상품의 옵션명이 변경되었습니다. 다시 주문해주세요.";

  public OrderItemOptionGroupNotFoundException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
