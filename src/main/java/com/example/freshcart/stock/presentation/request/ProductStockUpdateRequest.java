package com.example.freshcart.stock.presentation.request;

import com.example.freshcart.stock.application.command.ProductStockUpdateCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateRequest {
  private Long productId;
  private int quantity;

  public ProductStockUpdateCommand toCommand(){
    return new ProductStockUpdateCommand(this.productId, this.quantity);
  }
}
