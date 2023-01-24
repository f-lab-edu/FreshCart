package com.example.freshcart.stock.infrastructure;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.stock.application.StockReductionStrategy;
import com.example.freshcart.stock.domain.OptionStockRepository;
import com.example.freshcart.stock.domain.ProductStock;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.stock.domain.exception.ProductStockNotAvailableException;
import com.example.freshcart.stock.domain.exception.ProductStockNotFoundException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OptimisticLockStockReduction implements StockReductionStrategy {

  private final OptionStockRepository optionStockRepository;
  private final ProductStockRepository productStockRepository;

  @Override
  public void reduceProductInventory(OrderItem item, int count) {
    ProductStock productStock = productStockRepository.findByProductIdWithOptimisticLock(item.getProductId());
    if (productStock == null) {
      throw new ProductStockNotFoundException();
    }
    if(productStock.getQuantity() < count){
      throw new ProductStockNotAvailableException();
    }
    else {
      productStock.reduceStock(count);
    }
  }

  @Override
  public void reduceOptionInventory(OrderItemOption option, int count) {
  }
}
