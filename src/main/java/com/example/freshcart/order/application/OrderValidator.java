package com.example.freshcart.order.application;

import com.example.freshcart.order.domain.Order;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import lombok.NoArgsConstructor;


/**
 * OrderValidator
 * 역할: 제품의 이름, 옵션 그룹 등과 일치하는 이름으로 주문서를 작성했는지 등 확인 (중간에 제품이 수정되어 잘못된 데이터로 주문하는 것을 방지하기
 * 위해서)
 */

@NoArgsConstructor
public class OrderValidator {

  /**
   * Order가 비어있는지 확인한다.
   */
  public boolean validate(Order order) {
    if (order.getOrderItem().isEmpty()) {
      throw new IllegalArgumentException("주문할 제품을 담아주세요.");
    }
    return true;
  }

}
