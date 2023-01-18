package com.example.freshcart.order.infrastructure;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemRepository;
import com.example.freshcart.order.infrastructure.mybatis.OrderItemMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderItemMapperAdaptor implements OrderItemRepository {

  private final OrderItemMapper orderItemMapper;

  @Override
  public OrderItem save(OrderItem orderItem) {
    orderItemMapper.insert(orderItem);
    return orderItem;
  }
}
