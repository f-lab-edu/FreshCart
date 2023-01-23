package com.example.freshcart.stock.presentation.request;

import com.example.freshcart.stock.application.command.OptionStockUpdateCommand;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OptionStockUpdateRequest {

  @Positive(message = "재고 수량은 최소 0 개 이상 입니다 ")
  private int quantity;


  public OptionStockUpdateRequest(int quantity) {
    this.quantity = quantity;
  }


  public OptionStockUpdateCommand toCommand() {
    return new OptionStockUpdateCommand(this.quantity);
  }
}
