package com.example.freshcart.product.presentation.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * OptionGroup 등록 시 판매자의 요청값을 담는 DTO 객체 (ex.중량)
 * OptionGroup을 등록할 때는, Product 의 ID 를 알 수 없다. 등록 시 ProductId 를 제외한 DTO 객체
 */
public class OptionGroupRegister {
  @NotBlank(message = "옵션 그룹 이름을 입력해주세요")
  private String optionGroupName;
  @NotNull(message = "필수 옵션 여부를 입력해주세요")
  private boolean requiredOption;
  @NotBlank(message = "최소 주문 수량의 여부를 입력해주세요")
  private boolean minimumOrderOption;

  public OptionGroupRegister(String optionGroupName, boolean requiredOption,
      boolean minimumOrderOption) {
    this.optionGroupName = optionGroupName;
    this.requiredOption = requiredOption;
    this.minimumOrderOption = minimumOrderOption;
  }

  public String getOptionGroupName() {
    return optionGroupName;
  }

  public boolean isRequiredOption() {
    return requiredOption;
  }

  public boolean isMinimumOrderOption() {
    return minimumOrderOption;
  }


}
