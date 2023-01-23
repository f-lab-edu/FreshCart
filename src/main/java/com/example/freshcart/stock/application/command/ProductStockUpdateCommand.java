package com.example.freshcart.stock.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateCommand {

  private Long productId;
  private int quantity;
}
