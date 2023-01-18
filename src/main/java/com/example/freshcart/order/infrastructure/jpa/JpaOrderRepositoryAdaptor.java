package com.example.freshcart.order.infrastructure.jpa;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.order.domain.JpaOrderRepository;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaOrderRepositoryAdaptor implements OrderRepository {
  private final JpaOrderRepository jpaOrderRepository;

  @Override
  public Order save(LoginUser user, Order order) {
    jpaOrderRepository.save(order);
    return order;
  }
}
