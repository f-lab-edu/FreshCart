package com.example.freshcart.order.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.optionstock.application.OrderStockProcessor;
import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.ProductStock;
import com.example.freshcart.optionstock.domain.ProductStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotAvailableException;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotFoundException;
import com.example.freshcart.optionstock.domain.exception.ProductStockNotAvailableException;
import com.example.freshcart.optionstock.domain.exception.ProductStockNotFoundException;
import com.example.freshcart.order.application.command.CartCommand;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionRepository;
import com.example.freshcart.order.domain.OrderItemRepository;
import com.example.freshcart.order.domain.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 역할: 주문을 등록 협력 (1) CartToOrderMapper: 주문 요청 객체인 Cart를 Order로 변환. (2) orderValidator: 카트에 담은 이후 제품
 * 정보 등이 바뀌었을 경우를 대비하여, 옵션과 일치하는지 체크 (3) OrderRepository: 저장소와 data operation 작업 위임 OrderRepository와
 * OrderItemRegisterV1은 다른 구현체로 바뀔 가능성이 있다.
 */

@RequiredArgsConstructor
@Service
public class OrderRegisterProcessor {

  private final CartToOrderMapper cartToOrderMapper;
  private final OrderValidator orderValidator;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final OrderItemOptionRepository orderItemOptionRepository;
  private final OrderStockProcessor orderStockProcessor;

  @Transactional
  public void place(LoginUser user, CartCommand cart) {
    Order order = cartToOrderMapper.mapFrom(user, cart);
    orderValidator.validate(order);
    checkInventoryByOption(order.getOrderItem()); // Inventory 별도 필요
    save(user, order);
  }

  public void checkInventoryByOption(List<OrderItem> orderItem) {
    for (OrderItem item : orderItem) {
      int count = item.getCount();
      if (item.getOrderItemOption() == null) {
        orderStockProcessor.checkProductInventory(item, count);
      }
      if (item.getOrderItemOption() != null) {
        for (OrderItemOption option : item.getOrderItemOption()) {
          orderStockProcessor.checkInventory(option, count);
        }
      }
    }
  }


  public Order save(LoginUser user, Order order) {
    orderRepository.save(user, order);
    for (OrderItem orderItem : order.getOrderItem()) {
      orderItem.setOrderId(order.getId());
      orderItemRepository.save(orderItem);
      //option 이 없는 singleType일 경우 orderItemOptionGroups가 없다.
      if (orderItem.getOrderItemOption() != null) {
        for (OrderItemOption orderItemOption : orderItem.getOrderItemOption()) {
          orderItemOption.setOrderId(order.getId());
          orderItemOptionRepository.save(orderItemOption);
        }
      }
    }
    return order;
  }


}
