package com.example.freshcart.order.infrastructure.config;

import com.example.freshcart.order.application.OrderItemRegister;
import com.example.freshcart.order.domain.OrderRepository;
import com.example.freshcart.order.infrastructure.OrderMapper;
import com.example.freshcart.order.infrastructure.OrderRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRepositoryConfig {

  @Bean
  public OrderRepository orderRepository(OrderMapper orderMapper,
      OrderItemRegister orderItemRegister) {
    return new OrderRepositoryAdaptor(orderMapper, orderItemRegister);
  }
}

