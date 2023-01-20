package com.example.freshcart.optionstock.application;

import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.ProductStock;
import com.example.freshcart.optionstock.domain.ProductStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotAvailableException;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotFoundException;
import com.example.freshcart.optionstock.domain.exception.ProductStockNotAvailableException;
import com.example.freshcart.optionstock.domain.exception.ProductStockNotFoundException;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStockProcessor {

  private final OptionStockRepository optionStockRepository;
  private final ProductStockRepository productStockRepository;

  public void checkProductInventory(OrderItem item, int count) {
    ProductStock productStock = productStockRepository.findByProductIdWithPessimisticLock(item.getProductId());
    if (productStock == null) {
      throw new ProductStockNotFoundException();
    }
    if(productStock.getStock() < count){
      throw new ProductStockNotAvailableException();
    }
    else {
      productStock.reduceStock(count);
    }
  }

  public void checkInventory(OrderItemOption option, int count) {
    OptionStock optionStock = optionStockRepository.findByProductIdWithPessimisticLock(option.getOptionId());
    if (optionStock == null) {
      throw new OptionStockNotFoundException();
    }
    if (optionStock.getStock() < count) {
      throw new OptionStockNotAvailableException();
    } else {
      optionStock.reduceStock(count);
    }
  }
}
