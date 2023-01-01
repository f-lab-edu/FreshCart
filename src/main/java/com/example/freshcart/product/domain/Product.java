package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Product {

  private Long id;
  private String name;
  private int price;
  private Status status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String description;
  private boolean singleType;
  private int categoryId;
  private Long sellerId;

  private List<OptionGroup> optionGroupSpecs = new ArrayList<>();

  public enum Status {
    BEING_PREPARED, AVAILABLE, UNAVAILABLE,
  }

  @Builder
  public Product(String name, int price, Status status, String description, Boolean singleType,
      int categoryId, Long sellerId) {
    this.name = name;
    this.price = price;
    this.status = status;
    this.description = description;
    this.singleType = singleType;
    this.categoryId = categoryId;
    this.sellerId = sellerId;
  }
}
