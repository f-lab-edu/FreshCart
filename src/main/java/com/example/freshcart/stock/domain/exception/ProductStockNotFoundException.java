package com.example.freshcart.stock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProductStockNotFoundException extends BaseException {

  private static final String message = "존재하지 않는 제품 재고입니다.";

  public ProductStockNotFoundException() {
    super(HttpStatus.NOT_FOUND, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}