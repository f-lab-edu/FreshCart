package com.example.freshcart.stock.application;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;

public interface StockReductionStrategy {

  void reduceProductInventory(OrderItem item, int count);

  void reduceOptionInventory(OrderItemOption option, int count);
}
