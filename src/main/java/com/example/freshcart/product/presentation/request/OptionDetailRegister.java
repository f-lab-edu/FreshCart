package com.example.freshcart.product.presentation.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * 개별 Option (ex. 중량 OptionGroup에 해당하는 Option 50g) 최대 수량 제한이 없을 경우  null 가능. 최소 수량 - 주문할 경우 최소 수량임.
 * 따라서 적어도 1이상이어야 함.
 */
@NoArgsConstructor
@Getter
public class OptionDetailRegister {

  @NotBlank(message = "옵션 이름을 입력해주세요")
  private String optionName;
  @Range(min = 10, message = "상품 가격은 10원 이상이어야 합니다.")
  private int price;

  @Builder
  public OptionDetailRegister(String optionName, int price) {
    this.optionName = optionName;
    this.price = price;
  }

}
