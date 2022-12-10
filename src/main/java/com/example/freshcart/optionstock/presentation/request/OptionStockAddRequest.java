package com.example.freshcart.optionstock.presentation.request;

import com.example.freshcart.optionstock.application.command.OptionStockAddCommand;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class OptionStockAddRequest {

  @Positive(message = "재고 수량은 최소 0 개 이상 입니다 ")
  private int stock;

  public OptionStockAddRequest(int stock) {
    this.stock = stock;
  }

  public OptionStockAddRequest() {
  }

  public OptionStockAddCommand toCommand(){
    return new OptionStockAddCommand(this.stock);
  }
}
