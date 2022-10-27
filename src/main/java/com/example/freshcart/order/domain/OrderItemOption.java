package com.example.freshcart.order.domain;

public class OrderItemOption {
  private Long id;
  private Long productOptionId;
  private Long orderItemOptionGroupId;
  private String name;
  private int price;

  public OrderItemOption(Long productOptionId, String name, int price) {
    this.productOptionId = productOptionId;
    this.name = name;
    this.price = price;
  }

  public OrderItemOption() {
  }

  public Long getId() {
    return id;
  }

  public Long getProductOptionId() {
    return productOptionId;
  }

  public Long getOrderItemOptionGroupId() {
    return orderItemOptionGroupId;
  }

  public void setOrderItemOptionGroupId(Long orderItemOptionGroupId) {
    this.orderItemOptionGroupId = orderItemOptionGroupId;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }
}
