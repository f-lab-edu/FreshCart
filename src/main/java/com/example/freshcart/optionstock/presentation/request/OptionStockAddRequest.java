package com.example.freshcart.optionstock.presentation.request;

import com.example.freshcart.optionstock.application.command.OptionStockAddCommand;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
public class OptionStockAddRequest {

  @Range(min = 1, message = "optionId는 최소 1이상입니다")
  private Long optionId;

  @Positive(message = "재고 수량은 최소 0 개 이상 입니다 ")
  private int stock;

  public OptionStockAddCommand toCommand() {
    return new OptionStockAddCommand(this.optionId, this.stock);
  }
}
