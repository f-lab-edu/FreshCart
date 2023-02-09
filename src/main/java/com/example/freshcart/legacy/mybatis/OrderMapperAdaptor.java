package com.example.freshcart.legacy.mybatis;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderRepository;

/**
 * MyBatis 구현을 지원하는 Adaptor OrderItem, OrderItemOptionGroup 등은 OrderItemService에 위임한다.
 */

public class OrderMapperAdaptor implements OrderRepository {

  private final OrderMapper orderMapper;

  public OrderMapperAdaptor(OrderMapper orderMapper) {
    this.orderMapper = orderMapper;
  }


  @Override
  public Order save(LoginUser user, Order order) {
    orderMapper.insert(order);
    return order;
  }
}
