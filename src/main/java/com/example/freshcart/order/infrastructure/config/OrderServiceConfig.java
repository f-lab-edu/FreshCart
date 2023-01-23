package com.example.freshcart.order.infrastructure.config;

import com.example.freshcart.order.application.CartToOrderMapper;
import com.example.freshcart.order.application.OrderManagerFacade;
import com.example.freshcart.order.application.OrderRegisterProcessor;
import com.example.freshcart.order.application.OrderValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfig {

  @Bean
  public OrderManagerFacade orderManagerFacade(OrderRegisterProcessor orderRegisterProcessor) {
    return new OrderManagerFacade(orderRegisterProcessor);
  }

  @Bean
  public CartToOrderMapper cartToOrderMapper() {
    return new CartToOrderMapper();
  }

  @Bean
  public OrderValidator orderValidator() {
    return new OrderValidator();
  }

}
