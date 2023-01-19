package com.example.freshcart.optionstock.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateCommand {

  private Long productId;
  private int stock;
}
