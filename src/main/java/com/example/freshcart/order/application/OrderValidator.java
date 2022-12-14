package com.example.freshcart.order.application;

import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;
import com.example.freshcart.order.domain.exception.OrderItemNotFoundException;
import com.example.freshcart.order.domain.exception.OrderItemOptionGroupNotFoundException;
import com.example.freshcart.order.domain.exception.OrderItemOptionNotFoundException;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;


/**
 * OrderValidator
 * 역할: 제품의 이름, 옵션 그룹 등과 일치하는 이름으로 주문서를 작성했는지 등 확인 (중간에 제품이 수정되어 잘못된 데이터로 주문하는 것을 방지하기
 * 위해서)
 */

public class OrderValidator {

  private final ProductRepository productRepository;
  private final OptionGroupRepository optionGroupRepository;
  private final OptionRepository optionRepository;

  public OrderValidator(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository,
      OptionRepository optionRepository) {
    this.productRepository = productRepository;
    this.optionGroupRepository = optionGroupRepository;
    this.optionRepository = optionRepository;
  }

  /**
   * Order가 비어있는지 확인한다.
   */
  public boolean validate(Order order) {
    if (order.getOrderItemList().isEmpty()) {
      throw new IllegalArgumentException("주문할 제품을 담아주세요.");
    }

    /**
     * Order에 담긴 OrderLineItem을 하나씩 validate.
     * 이름이 일치하는 지 확인 필요
     */

    for (OrderItem orderItem : order.getOrderItemList()) {
      validateOrderLineItem(orderItem);
    }

    return true;
  }

  /**
   * 제품 이름이 변경되었다면, 유저가 주문하고자 하는 상품에 변경이 생긴 것이다. 이름이 일치한다면, 다음에는 OptionGroup을 확인.
   */
  public void validateOrderLineItem(OrderItem orderItem) {
    String productName = productRepository.findById(orderItem.getProductId()).getName();
    if (!orderItem.getName().equals(productName)) {
      throw new OrderItemNotFoundException();
    }

    for (OrderItemOptionGroup orderItemOptionGroup : orderItem.getOrderItemOptionGroups()) {
      validateOrderOptionGroup(orderItemOptionGroup);
    }
  }

  /**
   * OrderOptionGroup과 Option 내역이 Product의 것과 일치하는지 확인 OptionGroup이 있다면, List<Options>객체도 필수
   */
  public void validateOrderOptionGroup(OrderItemOptionGroup orderItemOptionGroup) {
    String optionGroupName = optionGroupRepository.findById(
            orderItemOptionGroup.getoptionGroupId())
        .getOptionGroupName();

    if (!orderItemOptionGroup.getName().equals(optionGroupName)) {
      throw new OrderItemOptionGroupNotFoundException();
    }

    if (orderItemOptionGroup.getOrderItemOptions().isEmpty()) {
      throw new IllegalArgumentException("제품의 상세 옵션을 지정해주세요.");
    }

    for (OrderItemOption orderItemOption : orderItemOptionGroup.getOrderItemOptions()) {
      validateOrderOption(orderItemOption);
    }
  }

  public void validateOrderOption(OrderItemOption orderItemOption) {
    String optionName = optionRepository.findById(orderItemOption.getOptionId())
        .getOptionName();
    if (!orderItemOption.getName().equals(optionName)) {
      throw new OrderItemOptionNotFoundException();
    }
  }
}
