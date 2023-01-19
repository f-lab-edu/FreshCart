package com.example.freshcart.optionstock.application.command;

import com.example.freshcart.optionstock.domain.ProductStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockAddCommand {

  private Long productId;
  private int stock;

  public static ProductStock of(Long productId, ProductStockAddCommand command, Long sellerId) {
    return new ProductStock(productId, command.getStock(), sellerId);
  }
}
