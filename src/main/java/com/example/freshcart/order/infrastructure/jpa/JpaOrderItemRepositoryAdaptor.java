package com.example.freshcart.order.infrastructure.jpa;

import com.example.freshcart.order.domain.JpaOrderItemRepository;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaOrderItemRepositoryAdaptor implements OrderItemRepository {

  private final JpaOrderItemRepository jpaOrderItemRepository;

  @Override
  public OrderItem save(OrderItem orderItem) {
    jpaOrderItemRepository.save(orderItem);
    return orderItem;
  }
}
