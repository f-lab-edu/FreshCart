package com.example.freshcart.order.infrastructure;

import com.example.freshcart.order.application.OrderItemService;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;


/**
 * MyBatis 전용 구현체 책임: Mapper를 통해 Order에 담긴 OrderItem 객체들을 각 테이블에 맞는 Mapper로 처리
 */
public class OrderItemServiceV1 implements OrderItemService {

  private final OrderItemMapper orderItemMapper;
  private final OrderItemOptionMapper orderItemOptionMapper;
  private final OrderItemOptionGroupMapper orderItemOptionGroupMapper;

  public OrderItemServiceV1(
      OrderItemMapper orderItemMapper,
      OrderItemOptionMapper orderItemOptionMapper,
      OrderItemOptionGroupMapper orderItemOptionGroupMapper) {
    this.orderItemMapper = orderItemMapper;
    this.orderItemOptionMapper = orderItemOptionMapper;
    this.orderItemOptionGroupMapper = orderItemOptionGroupMapper;
  }

  /**
   * OrderId가 OrderOptionGroup, OrderOption 등에 필요한지 확인 필요. List<OrderLineItem>으로 할 때랑 어떤 차이가 있는지
   * 테스트해보기. return 을 받아야 함. mapper 에 자동으로 생성되는 db id 등이 저장되어있어야 함. 3중 for 문 성능 개선 가능한지 체크
   */

  @Override
  public Order save(Order order) {
    for (OrderItem orderItem : order.getOrderItemList()) {
      orderItem.setOrderId(order.getId());
      orderItemMapper.insert(orderItem);
      //option 이 없는 singleType일 경우 orderItemOptionGroups가 없다.
      if (orderItem.getOrderItemOptionGroups() != null) {
        for (OrderItemOptionGroup orderItemOptionGroup : orderItem.getOrderItemOptionGroups()) {
          orderItemOptionGroup.setOrderItemId(orderItem.getId());
          orderItemOptionGroupMapper.insert(orderItemOptionGroup);
          for (OrderItemOption orderItemOption : orderItemOptionGroup.getOrderItemOptions()) {
            orderItemOption.setOrderItemOptionGroupId(orderItemOptionGroup.getId());
            orderItemOptionMapper.insert(orderItemOption);
          }
        }
      }
    }
    return order;
  }
}

