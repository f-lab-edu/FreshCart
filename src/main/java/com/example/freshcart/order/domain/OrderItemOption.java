package com.example.freshcart.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemOption {
  private Long orderId;
  private Long optionId;

  public OrderItemOption(Long optionId) {
    this.optionId = optionId;
  }

  public void setOrderId(Long id) {
    this.orderId = id;
  }
}
