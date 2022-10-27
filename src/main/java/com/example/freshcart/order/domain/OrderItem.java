package com.example.freshcart.order.domain;

import java.util.List;

public class OrderItem {

  private Long id;
  private Long productId;
  private Long orderId;
  private String name;
  private int price;
  private int count;
  private List<OrderItemOptionGroup> orderItemOptionGroups;

  public OrderItem() {
  }


  public OrderItem(Long productId, String name, int price, int count,
      List<OrderItemOptionGroup> orderItemOptionGroups) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.count = count;
    this.orderItemOptionGroups = orderItemOptionGroups;
  }

  public OrderItem(Long productId, Long orderId, String name, int price, int count) {
    this.productId = productId;
    this.orderId = orderId;
    this.name = name;
    this.price = price;
    this.count = count;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getCount() {
    return count;
  }

  public List<OrderItemOptionGroup> getOrderItemOptionGroups() {
    return orderItemOptionGroups;
  }
}
