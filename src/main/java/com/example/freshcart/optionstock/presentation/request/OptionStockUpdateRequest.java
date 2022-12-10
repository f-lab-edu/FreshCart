package com.example.freshcart.optionstock.presentation.request;

import com.example.freshcart.optionstock.application.command.OptionStockUpdateCommand;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class OptionStockUpdateRequest {

  @Positive(message = "재고 수량은 최소 0 개 이상 입니다 ")
  private int stock;


  public OptionStockUpdateRequest(int stock) {
    this.stock = stock;
  }

  public OptionStockUpdateRequest() {
  }

  public OptionStockUpdateCommand toCommand() {
    return new OptionStockUpdateCommand(this.stock);
  }
}
