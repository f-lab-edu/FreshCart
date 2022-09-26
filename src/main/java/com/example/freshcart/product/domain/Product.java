package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Product {

  private Long id;
  private String name;
  private int price;
  private Status status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String description;
  private Boolean singleType;
  private int categoryId;
  private Long userId;

  private List<OptionGroup> optionGroupSpecs = new ArrayList<>();

  public enum Status {
    BEING_PREPARED, AVAILABLE, UNAVAILABLE,
  }

  public Product(String name, int price, Status status, String description, Boolean singleType,
      int categoryId, Long userId) {
    this.name = name;
    this.price = price;
    this.status = status;
    this.description = description;
    this.singleType = singleType;
    this.categoryId = categoryId;
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public Status getStatus() {
    return status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getSingleType() {
    return singleType;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public Long getUserId() {
    return userId;
  }
}
