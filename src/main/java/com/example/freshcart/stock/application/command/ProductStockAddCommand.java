package com.example.freshcart.stock.application.command;

import com.example.freshcart.stock.domain.ProductStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockAddCommand {

  private Long productId;
  private int quantity;

  public static ProductStock of(Long productId, ProductStockAddCommand command, Long sellerId) {
    return new ProductStock(productId, command.getQuantity(), sellerId);
  }
}
