package com.example.freshcart.product.presentation.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * 개별 Option (ex. 중량 OptionGroup에 해당하는 Option 50g)
 * 최대 수량 제한이 없을 경우  null 가능.
 * 최소 수량 - 주문할 경우 최소 수량임. 따라서 적어도 1이상이어야 함.
 */
public class OptionDetailRegister {

  @NotBlank(message = "옵션 이름을 입력해주세요")
  private String optionName;
  @Positive(message = "가격은 0 이상이어야 합니다")
  private int price;
  @Positive(message = "최소 주문 수량은 0 이상이어야 합니다")
  private int minimumOrder;

  private int maximumOrder;

  public OptionDetailRegister(String optionName, int price, int minimumOrder, int maximumOrder) {
    this.optionName = optionName;
    this.price = price;
    this.minimumOrder = minimumOrder;
    this.maximumOrder = maximumOrder;
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

}
