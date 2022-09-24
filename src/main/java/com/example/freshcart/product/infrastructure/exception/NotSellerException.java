package com.example.freshcart.product.infrastructure.exception;

import com.example.freshcart.user.infrastructure.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotSellerException extends BaseException {

  // static 변수 할당으로 인스턴스를 생성하지 않고도 사용 가능하게 함. 변수가 super() 생성자에서도 참조되기 위함.
  private static final String message = "판매자만 이용 가능합니다";

  public NotSellerException() {
    super(HttpStatus.UNAUTHORIZED, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
