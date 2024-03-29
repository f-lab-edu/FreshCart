package com.example.freshcart.stock.domain.exception;

import com.example.freshcart.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OptionStockExistsExcecption extends BaseException {

  private static final String message = "이미 재고가 등록 되어 있습니다. 수량 변경 요청해주세요";

  public OptionStockExistsExcecption() {
    super(HttpStatus.BAD_REQUEST, message);
  }

  @Override
  public String getMessage() {
    return message;
  }

}