package com.example.freshcart.order.infrastructure;

import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionRepository;
import com.example.freshcart.order.infrastructure.mybatis.OrderItemOptionMapper;
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
