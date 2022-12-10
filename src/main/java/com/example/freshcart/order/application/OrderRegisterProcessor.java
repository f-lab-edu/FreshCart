package com.example.freshcart.order.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.optionstock.application.OptionStockService;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotAvailableException;
import com.example.freshcart.order.application.command.CartCommand;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;
import com.example.freshcart.order.domain.OrderRepository;
import java.util.List;


/**
 * 역할: 주문을 등록 협력 (1) CartToOrderMapper: 주문 요청 객체인 Cart를 Order로 변환. (2) orderValidator: 카트에 담은 이후 제품
 * 정보 등이 바뀌었을 경우를 대비하여, 옵션과 일치하는지 체크 (3) OrderRepository: 저장소와 data operation 작업 위임 OrderRepository와
 * OrderItemRegisterV1은 다른 구현체로 바뀔 가능성이 있다.
 *
 * 우선 3중 for 문으로라도 inventory check 되게 만들기.
 */

public class OrderRegisterProcessor {

  private final CartToOrderMapper cartToOrderMapper;
  private final OrderValidator orderValidator;
  private final OptionStockService optionStockService;
  private final OrderRepository orderRepository;
  private final OptionStockRepository optionStockRepository;

  public OrderRegisterProcessor(
      CartToOrderMapper cartToOrderMapper,
      OrderValidator orderValidator,
      OptionStockService optionStockService,
      OrderRepository orderRepository,
      OptionStockRepository optionStockRepository) {
    this.cartToOrderMapper = cartToOrderMapper;
    this.orderValidator = orderValidator;
    this.optionStockService = optionStockService;
    this.orderRepository = orderRepository;
    this.optionStockRepository = optionStockRepository;
  }


  public void place(LoginUser user, CartCommand cart) {
    Order order = cartToOrderMapper.mapFrom(user, cart);
    orderValidator.validate(order);
    convertToOptionList(order.getOrderItemList()); // Inventory 별도 필요
    save(user, order);
  }

  //주문서에 재고가 없는 제품이 있을 경우, Exception

//  public List<OrderItemOption> mapToOptionList(List<OrderItem> orderItemList){
//    orderItemList.stream().map(orderItem -> OrderItem.getOrderItemOptionGroups()).collect(toList());
//    return new
//  }


  public void convertToOptionList(List<OrderItem> orderItemList) {
    for(OrderItem x: orderItemList){
      int count = x.getCount();
      List<OrderItemOptionGroup> orderItemOptionGroups = x.getOrderItemOptionGroups();
      for(OrderItemOptionGroup y: orderItemOptionGroups){
        List<OrderItemOption> orderItemOptionList = y.getOrderItemOptions();
        for(OrderItemOption z: orderItemOptionList){
          checkInventory(z, count);
        }
      }
    }

    //orderItemList의 orderItem을 하나씩 꺼내서, OrderItem의 getOptionGroup을 가져오고, OrderItemOptionGroup에서 OrderItemOption을 꺼낸다.
    //OrderItemOption의 productOptionId를 찾는다.
    //Inventory로 조회해보면서, 재고가 있는지 확인한다.

//    orderItemList.getOrderItem().getOrderItemOptionGroups().get
//
//    orderItemList.forEach(orderItem -> {
//      if () {
//        throw new OptionStockNotAvailableException();
//      }
//    });
  }

  public void checkInventory(OrderItemOption option, int count){
    if (optionStockRepository.findById(option.getProductOptionId()).getStock()<count) {
      throw new OptionStockNotAvailableException();
    }
  }

  public Order save(LoginUser user, Order order) {
    orderRepository.save(user, order);
    return order;
  }


}
