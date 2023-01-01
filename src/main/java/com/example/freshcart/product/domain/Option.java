package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Option {

  private Long id;
  private String name;
  private int price;
  private Long optionGroupId;
  private Long sellerId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  public Option(String name, int price, Long optionGroupId, Long sellerId) {
    this.name = name;
    this.price = price;
    this.optionGroupId = optionGroupId;
    this.sellerId = sellerId;
  }

}
