package com.example.freshcart.order.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.order.application.command.CartCommand;
import org.springframework.transaction.annotation.Transactional;


/**
 * 추후 주문 검색 등이 필요할 때는, OrderSearchProcessor를 별도로 생성한다.
 */

public class OrderManagerFacade {

  private final OrderRegisterProcessor orderRegisterProcessor;

  public OrderManagerFacade(OrderRegisterProcessor orderRegisterProcessor) {
    this.orderRegisterProcessor = orderRegisterProcessor;
  }

  @Transactional
  public void register(LoginUser user, CartCommand cart) {
    orderRegisterProcessor.place(user, cart);
  }

}
