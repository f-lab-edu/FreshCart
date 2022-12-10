package com.example.freshcart.optionstock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OptionStockNotAvailableException extends BaseException {

  // static 변수 할당으로 인스턴스를 생성하지 않고도 사용 가능하게 함. 변수가 super() 생성자에서도 참조되기 위함.
  private static final String message = "존재하지 않는 재고 입니다";

  public OptionStockNotAvailableException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
