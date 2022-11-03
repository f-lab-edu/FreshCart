package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OptionGroup {

  private Long id;
  private String optionGroupName;
  //필수 옵션 선택 여부
  private boolean isRequired;
  //배타 선택 여부: true 일 경우 min max order 모두 1임.
  private boolean exclusive;
  private int minimumOrder;
  private int maximumOrder;
  private Long productId;
  private List<Option> options;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public OptionGroup() {
  }

  public OptionGroup(String optionGroupName, boolean isRequired, boolean exclusive,
      int minimumOrder, int maximumOrder, Long productId) {
    this.optionGroupName = optionGroupName;
    this.isRequired = isRequired;
    this.exclusive = exclusive;
    this.minimumOrder = minimumOrder;
    this.maximumOrder = maximumOrder;
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

  public boolean exclusive() {
    return exclusive;
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

  public int getMinimumOrder() {
    return minimumOrder;
  }

  public int getMaximumOrder() {
    return maximumOrder;
  }
}
