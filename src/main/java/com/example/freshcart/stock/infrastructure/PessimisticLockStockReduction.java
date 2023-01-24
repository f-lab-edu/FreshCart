package com.example.freshcart.stock.infrastructure;

import com.example.freshcart.stock.application.StockReductionStrategy;
import com.example.freshcart.stock.domain.OptionStock;
import com.example.freshcart.stock.domain.OptionStockRepository;
import com.example.freshcart.stock.domain.ProductStock;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.stock.domain.exception.OptionStockNotAvailableException;
import com.example.freshcart.stock.domain.exception.OptionStockNotFoundException;
import com.example.freshcart.stock.domain.exception.ProductStockNotAvailableException;
import com.example.freshcart.stock.domain.exception.ProductStockNotFoundException;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class PessimisticLockStockReduction implements StockReductionStrategy {

  private final OptionStockRepository optionStockRepository;
  private final ProductStockRepository productStockRepository;

  @Override
  public void reduceProductInventory(OrderItem item, int count) {
    ProductStock productStock = productStockRepository.findByProductIdWithPessimisticLock(item.getProductId());
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
    OptionStock optionStock = optionStockRepository.findByOptionIdWithPessimisticLock(option.getOptionId());
    if (optionStock == null) {
      throw new OptionStockNotFoundException();
    }
    if (optionStock.getQuantity() < count) {
      throw new OptionStockNotAvailableException();
    } else {
      optionStock.reduceStock(count);
    }
  }
}
