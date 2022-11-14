package com.example.freshcart.order.infrastructure;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.order.application.OrderItemRegister;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderRepository;

/**
 * MyBatis 구현을 지원하는 Adaptor OrderItem, OrderItemOptionGroup 등은 OrderItemService에 위임한다.
 */

public class OrderRepositoryAdaptor implements OrderRepository {

  private final OrderMapper orderMapper;
  private final OrderItemRegister orderItemRegister;

  public OrderRepositoryAdaptor(OrderMapper orderMapper,
      OrderItemRegister orderItemRegister) {
    this.orderMapper = orderMapper;
    this.orderItemRegister = orderItemRegister;
  }


  @Override
  public Order save(LoginUser user, Order order) {
    orderMapper.insert(order);
    orderItemRegister.save(order);
    return order;
  }
}
