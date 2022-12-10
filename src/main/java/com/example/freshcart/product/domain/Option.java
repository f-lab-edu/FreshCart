package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Option {

  private Long id;
  private String optionName;
  private int price;
  private Long optionGroupId;
  private Long sellerId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Option(String optionName, int price,
      Long optionGroupId) {
    this.optionName = optionName;
    this.price = price;
    this.optionGroupId = optionGroupId;
  }

  public Option(String optionName, int price, Long optionGroupId, Long sellerId) {
    this.optionName = optionName;
    this.price = price;
    this.optionGroupId = optionGroupId;
    this.sellerId = sellerId;
  }

}
