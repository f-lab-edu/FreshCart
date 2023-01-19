package com.example.freshcart.optionstock.application.command;

import com.example.freshcart.optionstock.domain.OptionStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionStockAddCommand {

  private Long optionId;

  private int stock;

  public static OptionStock of(Long sellerId, Long optionId, OptionStockAddCommand command) {
    return new OptionStock(sellerId, optionId, command.getStock());
  }
}
