package com.example.freshcart.order.infrastructure.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OrderItemNotFoundException extends BaseException {

  // static 변수 할당으로 인스턴스를 생성하지 않고도 사용 가능하게 함. 변수가 super() 생성자에서도 참조되기 위함.
  private static final String message = "상품이 변경되었습니다. 다시 주문해주세요";

  public OrderItemNotFoundException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;

  }
}