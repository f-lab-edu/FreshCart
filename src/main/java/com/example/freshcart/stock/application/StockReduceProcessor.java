package com.example.freshcart.stock.application;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockReduceProcessor {

  private final StockReductionStrategy stockReductionStrategy;

  public void reduceInventory(List<OrderItem> orderItem) {
    for (OrderItem item : orderItem) {
      int count = item.getCount();
      if (item.getOrderItemOption() == null) {
        stockReductionStrategy.reduceProductInventory(item, count);
      }
      if (item.getOrderItemOption() != null) {
        for (OrderItemOption option : item.getOrderItemOption()) {
          stockReductionStrategy.reduceOptionInventory(option, count);
        }
      }
    }
  }
}
