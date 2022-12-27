package com.example.freshcart.order.domain;

public class OrderItemOption {
  private Long id;
  private Long optionId;
  private Long orderItemOptionGroupId;
  private String name;
  private int price;

  public OrderItemOption(Long optionId, String name, int price) {
    this.optionId = optionId;
    this.name = name;
    this.price = price;
  }

  public OrderItemOption() {
  }

  public Long getId() {
    return id;
  }

  public Long getOptionId() {
    return optionId;
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
