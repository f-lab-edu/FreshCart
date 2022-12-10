package com.example.freshcart.optionstock.application.command;

import com.example.freshcart.optionstock.domain.OptionStock;
import lombok.Getter;

@Getter
public class OptionStockAddCommand {

  private int stock;

  public OptionStockAddCommand(int stock) {
    this.stock = stock;
  }

  public OptionStockAddCommand() {
  }

  public static OptionStock of(Long sellerId, Long optionId, OptionStockAddCommand command) {
    return new OptionStock(sellerId, optionId, command.getStock());
  }
}
