package com.example.freshcart.product.domain;

import java.time.LocalDateTime;

public class Option {

  private Long id;
  private String optionName;
  private int price;
  private Long optionGroupId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Option() {
  }

  public Option(String optionName, int price,
      Long optionGroupId) {
    this.optionName = optionName;
    this.price = price;
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
