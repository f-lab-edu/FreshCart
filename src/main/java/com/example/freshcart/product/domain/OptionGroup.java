package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
  private Long sellerId;
  private List<Option> options = new ArrayList<>();
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public OptionGroup(String optionGroupName, boolean isRequired, boolean exclusive,
      int minimumOrder, int maximumOrder, Long productId, Long sellerId) {
    this.optionGroupName = optionGroupName;
    this.isRequired = isRequired;
    this.exclusive = exclusive;
    this.minimumOrder = minimumOrder;
    this.maximumOrder = maximumOrder;
    this.productId = productId;
    this.sellerId = sellerId;
  }
}
