package com.example.freshcart.product.domain;

import java.time.LocalDateTime;

public class Option {

  private Long id;
  private String optionName;
  private int price;
  private int minimumOrder;
  private int maximumOrder;
  private Long optionGroupId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public Option(String optionName, int price, int minimumOrder, int maximumOrder,
      Long optionGroupId) {
    this.optionName = optionName;
    this.price = price;
    this.minimumOrder = minimumOrder;
    this.maximumOrder = maximumOrder;
    this.optionGroupId = optionGroupId;
  }



  public Long getId() {
    return id;
  }

  public String getOptionName() {
    return optionName;
  }

  public int getPrice() {
    return price;
  }

  public int getMinimumOrder() {
    return minimumOrder;
  }

  public int getMaximumOrder() {
    return maximumOrder;
  }

  public Long getOptionGroupId() {
    return optionGroupId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
