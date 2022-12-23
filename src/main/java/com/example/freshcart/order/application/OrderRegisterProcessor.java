package com.example.freshcart.order.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.optionstock.application.OptionStockService;
import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotAvailableException;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotFoundException;
import com.example.freshcart.order.application.command.CartCommand;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;
import com.example.freshcart.order.domain.OrderRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


/**
 * 역할: 주문을 등록 협력
 * (1) CartToOrderMapper: 주문 요청 객체인 Cart를 Order로 변환.
 * (2) orderValidator: 카트에 담은 이후 제품
 * 정보 등이 바뀌었을 경우를 대비하여, 옵션과 일치하는지 체크 (3) OrderRepository: 저장소와 data operation 작업 위임 OrderRepository와
 * OrderItemRegisterV1은 다른 구현체로 바뀔 가능성이 있다.
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


  @Transactional
  public void place(LoginUser user, CartCommand cart) {
    Order order = cartToOrderMapper.mapFrom(user, cart);
    orderValidator.validate(order);
    convertToOptionList(order.getOrderItemList()); // Inventory 별도 필요
    save(user, order);
  }

  public void convertToOptionList(List<OrderItem> orderItemList) {
    for (OrderItem x : orderItemList) {
      int count = x.getCount();
      List<OrderItemOptionGroup> orderItemOptionGroups = x.getOrderItemOptionGroups();
      for (OrderItemOptionGroup y : orderItemOptionGroups) {
        List<OrderItemOption> orderItemOptionList = y.getOrderItemOptions();
        for (OrderItemOption z : orderItemOptionList) {
          checkInventory(z, count);
        }
      }
    }
  }

  public void checkInventory(OrderItemOption option, int count) {
    OptionStock optionStock = optionStockRepository.findById(option.getOptionId());
    if (optionStock == null) {
      throw new OptionStockNotFoundException();
    }
    if (optionStock.getStock() < count) {
      throw new OptionStockNotAvailableException();
    } else {
      optionStock.reduceStock(count);
    }
  }

  public Order save(LoginUser user, Order order) {
    orderRepository.save(user, order);
    return order;
  }


}
