package com.example.freshcart.stock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProductStockNotAvailableException extends BaseException {

  private static final String message = "제품의 재고가 부족합니다.";

  public ProductStockNotAvailableException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
