package com.example.freshcart.order.infrastructure.config;

import com.example.freshcart.order.application.CartToOrderMapper;
import com.example.freshcart.order.application.OrderItemRegister;
import com.example.freshcart.order.application.OrderManagerFacade;
import com.example.freshcart.order.application.OrderRegisterProcessor;
import com.example.freshcart.order.application.OrderValidator;
import com.example.freshcart.order.domain.OrderRepository;
import com.example.freshcart.order.infrastructure.OrderItemMapper;
import com.example.freshcart.order.infrastructure.OrderItemOptionGroupMapper;
import com.example.freshcart.order.infrastructure.OrderItemOptionMapper;
import com.example.freshcart.order.infrastructure.OrderItemRegisterV1;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfig {

  @Bean
  public OrderManagerFacade orderManagerFacade(OrderRegisterProcessor orderRegisterProcessor) {
    return new OrderManagerFacade(orderRegisterProcessor);
  }

  @Bean
  public OrderItemRegister orderItemService(OrderItemMapper orderItemMapper,
      OrderItemOptionMapper orderItemOptionMapper,
      OrderItemOptionGroupMapper orderItemOptionGroupMapper) {
    return new OrderItemRegisterV1(orderItemMapper, orderItemOptionMapper,
        orderItemOptionGroupMapper);
  }

  @Bean
  public CartToOrderMapper cartToOrderMapper() {
    return new CartToOrderMapper();
  }

  @Bean
  public OrderValidator orderValidator(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository, OptionRepository optionRepository) {
    return new OrderValidator(productRepository, optionGroupRepository, optionRepository);
  }

  @Bean
  public OrderRegisterProcessor orderRegisterProcessor(CartToOrderMapper cartToOrderMapper,
      OrderValidator orderValidator, OrderRepository orderRepository) {
    return new OrderRegisterProcessor(cartToOrderMapper, orderValidator, orderRepository);
  }
}
