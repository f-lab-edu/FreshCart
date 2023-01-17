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
    return true;
  }

}
