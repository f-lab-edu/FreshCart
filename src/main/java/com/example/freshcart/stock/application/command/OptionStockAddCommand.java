package com.example.freshcart.stock.application.command;

import com.example.freshcart.stock.domain.OptionStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionStockAddCommand {

  private Long optionId;

  private int quantity;

  public static OptionStock of(Long sellerId, Long optionId, OptionStockAddCommand command) {
    return new OptionStock(sellerId, optionId, command.getQuantity());
  }
}
