package com.example.freshcart.order.infrastructure.jpa;

import com.example.freshcart.order.domain.JpaOrderItemOptionRepository;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaOrderItemOptionRepositoryAdaptor implements OrderItemOptionRepository {
  private final JpaOrderItemOptionRepository jpaOrderItemOptionRepository;

  @Override
  public OrderItemOption save(OrderItemOption orderItemOption) {
    jpaOrderItemOptionRepository.save(orderItemOption);
    return orderItemOption;
  }
}
