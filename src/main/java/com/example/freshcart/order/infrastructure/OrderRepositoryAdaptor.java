package com.example.freshcart.order.infrastructure;

import com.example.freshcart.order.application.OrderItemService;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderRepository;
import com.example.freshcart.user.application.LoginUser;

/**
 * MyBatis 구현을 지원하는 Adaptor OrderItem, OrderItemOptionGroup 등은 OrderItemService에 위임한다.
 */

public class OrderRepositoryAdaptor implements OrderRepository {

  private final OrderMapper orderMapper;
  private final OrderItemService orderItemService;

  public OrderRepositoryAdaptor(OrderMapper orderMapper,
      OrderItemService orderItemService) {
    this.orderMapper = orderMapper;
    this.orderItemService = orderItemService;
  }


  @Override
  public Order save(LoginUser user, Order order) {
    orderMapper.insert(order);
    orderItemService.save(order);
    return order;
  }
}
