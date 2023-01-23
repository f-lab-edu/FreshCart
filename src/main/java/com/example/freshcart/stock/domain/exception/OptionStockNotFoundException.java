package com.example.freshcart.stock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OptionStockNotFoundException extends BaseException {

  private static final String message = "존재하지 않는 옵션 재고 입니다";

  public OptionStockNotFoundException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
