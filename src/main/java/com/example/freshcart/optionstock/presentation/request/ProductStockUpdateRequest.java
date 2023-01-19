package com.example.freshcart.optionstock.presentation.request;

import com.example.freshcart.optionstock.application.command.ProductStockUpdateCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateRequest {
  private Long productId;
  private int stock;

  public ProductStockUpdateCommand toCommand(){
    return new ProductStockUpdateCommand(this.productId, this.stock);
  }
}
