package com.example.freshcart.order.domain;

import java.util.List;

/**
 * productOptionGroupId가 꼭 필요한가? 넣은 이유: 테이블 생성 시 외래 키 참조 위해.
 */
public class OrderItemOptionGroup {

  private Long id;
  private Long productOptionGroupId;
  private String name;
  private Long orderItemId;
  private List<OrderItemOption> orderItemOptions;

  public OrderItemOptionGroup(Long productOptionGroupId, String name,
      List<OrderItemOption> orderItemOptions) {
    this.productOptionGroupId = productOptionGroupId;
    this.name = name;
    this.orderItemOptions = orderItemOptions;
  }


  public Long getId() {
    return id;
  }

  public Long getProductOptionGroupId() {
    return productOptionGroupId;
  }

  public String getName() {
    return name;
  }

  public List<OrderItemOption> getOrderItemOptions() {
    return orderItemOptions;
  }

  public Long getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(Long orderItemId) {
    this.orderItemId = orderItemId;
  }
}
