package com.example.freshcart.optionstock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OptionStockNotAvailableException extends BaseException {

  private static final String message = "옵션의 재고가 부족합니다.";

  public OptionStockNotAvailableException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
