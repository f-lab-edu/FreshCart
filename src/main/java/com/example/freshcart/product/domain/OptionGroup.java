package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OptionGroup {

  private Long id;
  private String optionGroupName;
  private boolean isRequired;
  private boolean isCountRequired;
  private Long productId;
  private List<Option> options;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public OptionGroup(String optionGroupName, boolean isRequired, boolean isCountRequired,
      Long productId) {
    this.optionGroupName = optionGroupName;
    this.isRequired = isRequired;
    this.isCountRequired = isCountRequired;
    this.productId = productId;
  }

  public Long getId() {
    return id;
  }

  public String getOptionGroupName() {
    return optionGroupName;
  }

  public boolean isRequired() {
    return isRequired;
  }

  public boolean isCountRequired() {
    return isCountRequired;
  }

  public Long getProductId() {
    return productId;
  }

  public List<Option> getOptions() {
    return options;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
