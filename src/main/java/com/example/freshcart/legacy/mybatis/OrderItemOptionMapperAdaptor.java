package com.example.freshcart.legacy.mybatis;

import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderItemOptionMapperAdaptor implements OrderItemOptionRepository {

  private final OrderItemOptionMapper orderItemOptionMapper;

  @Override
  public OrderItemOption save(OrderItemOption orderItemOption) {
    orderItemOptionMapper.insert(orderItemOption);
    return orderItemOption;
  }
}
