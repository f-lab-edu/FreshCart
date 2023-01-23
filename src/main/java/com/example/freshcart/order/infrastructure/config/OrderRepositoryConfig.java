package com.example.freshcart.order.infrastructure.config;

import com.example.freshcart.order.domain.JpaOrderItemOptionRepository;
import com.example.freshcart.order.domain.JpaOrderItemRepository;
import com.example.freshcart.order.domain.JpaOrderRepository;
import com.example.freshcart.order.domain.OrderItemOptionRepository;
import com.example.freshcart.order.domain.OrderItemRepository;
import com.example.freshcart.order.domain.OrderRepository;
import com.example.freshcart.order.infrastructure.jpa.JpaOrderItemOptionRepositoryAdaptor;
import com.example.freshcart.order.infrastructure.jpa.JpaOrderItemRepositoryAdaptor;
import com.example.freshcart.order.infrastructure.jpa.JpaOrderRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRepositoryConfig {

//MyBatis
//  @Bean
//  public OrderRepository orderRepository(OrderMapper orderMapper) {
//    return new OrderMapperAdaptor(orderMapper);
//  }
//
//  @Bean
//  public OrderItemRepository orderItemRepository(OrderItemMapper orderItemMapper) {
//    return new OrderItemMapperAdaptor(orderItemMapper);
//  }
//
//  @Bean
//  public OrderItemOptionRepository orderItemOptionRepository(
//      OrderItemOptionMapper orderItemOptionMapper) {
//    return new OrderItemOptionMapperAdaptor(orderItemOptionMapper);
//  }

  @Bean
  public OrderRepository orderRepository(JpaOrderRepository jpaOrderRepository) {
    return new JpaOrderRepositoryAdaptor(jpaOrderRepository);
  }

  @Bean
  public OrderItemRepository orderItemRepository(JpaOrderItemRepository jpaOrderItemRepository) {
    return new JpaOrderItemRepositoryAdaptor(jpaOrderItemRepository);
  }

  @Bean
  public OrderItemOptionRepository orderItemOptionRepository(
      JpaOrderItemOptionRepository jpaOrderItemOptionRepository) {
    return new JpaOrderItemOptionRepositoryAdaptor(jpaOrderItemOptionRepository);
  }
}

