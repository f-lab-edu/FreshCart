package com.example.freshcart.order.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OrderItemOptionNotFoundException extends BaseException {

  private static final String message = "상품 옵션이 변경되었습니다. 다시 주문해주세요.";

  public OrderItemOptionNotFoundException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
