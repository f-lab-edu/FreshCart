package com.example.freshcart.order.application;

import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderRepository;
import com.example.freshcart.order.presentation.request.Cart;
import com.example.freshcart.user.application.LoginUser;


/**
 * 역할: 주문을 등록
 * 협력
 * (1)  CartToOrderMapper: 주문 요청 객체인 Cart를 Order로 변환.
 * (2) orderValidator: 카트에 담은 이후 제품 정보 등이 바뀌었을 경우를 대비하여, 옵션과 일치하는지 체크
 * (3) OrderRepository: 저장소와 data operation 작업 위임
 * OrderRepository와 OrderItemServiceImpl은 다른 구현체로 바뀔 가능성이 있다.
 */

public class OrderRegisterProcessor {

  private final CartToOrderMapper cartToOrderMapper;
  private final OrderValidator orderValidator;
  private final OrderRepository orderRepository;

  public OrderRegisterProcessor(
      CartToOrderMapper cartToOrderMapper,
      OrderValidator orderValidator,
      OrderRepository orderRepository) {
    this.cartToOrderMapper = cartToOrderMapper;
    this.orderValidator = orderValidator;
    this.orderRepository = orderRepository;
  }

  public void place(LoginUser user, Cart cart) {
    Order order = cartToOrderMapper.mapFrom(user, cart);
    orderValidator.validate(order);
    checkInventory(); // Inventory 별도 필요
    save(user, order);
  }

  public void checkInventory(){

  }
  public Order save(LoginUser user, Order order) {
    orderRepository.save(user, order);
    return order;
  }



}
