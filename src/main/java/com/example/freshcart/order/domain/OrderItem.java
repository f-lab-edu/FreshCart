package com.example.freshcart.order.domain;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItem {

  private Long id;
  private Long productId;
  private Long orderId;
  private int count;
  private List<OrderItemOption> orderItemOptions;

  public OrderItem(Long productId, int count,
      List<OrderItemOption> orderItemOptions) {
    this.productId = productId;
    this.count = count;
    this.orderItemOptions = orderItemOptions;
  }


  public void setOrderId(Long id) {
    this.orderId = id;
  }
}
