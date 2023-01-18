package com.example.freshcart.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item_option")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long orderId;
  private Long optionId;

  public OrderItemOption(Long optionId) {
    this.optionId = optionId;
  }

  public void setOrderId(Long Id) {
    this.orderId = Id;
  }
}
