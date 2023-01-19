package com.example.freshcart.optionstock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProductStockExistsExcecption extends BaseException {

  private static final String message = "이미 재고가 등록된 상품입니다. 수량 변경 요청해주세요";

  public ProductStockExistsExcecption() {
    super(HttpStatus.BAD_REQUEST, message);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
