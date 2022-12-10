package com.example.freshcart.optionstock.application.command;

import lombok.Getter;

@Getter
public class OptionStockUpdateCommand {
  private int stock;

  public OptionStockUpdateCommand() {
  }

  public OptionStockUpdateCommand(int stock) {
    this.stock = stock;
  }
}
