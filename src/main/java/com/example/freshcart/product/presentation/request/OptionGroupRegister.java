package com.example.freshcart.product.presentation.request;


import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.Product;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

/**
 * OptionGroup 등록 시 판매자의 요청값을 담는 DTO 객체 (ex.중량) OptionGroup을 등록할 때는, Product 의 ID 를 알 수 없다. 등록 시
 * ProductId 를 제외한 DTO 객체
 */
public class OptionGroupRegister {

  @NotBlank(message = "옵션 그룹 이름을 입력해주세요")
  private String optionGroupName;
  @NotNull(message = "필수 옵션 여부를 입력해주세요")
  private boolean requiredOption;
  @NotNull(message = "배타 선택 여부를 입력해주세요")
  private boolean exclusive;
  @Range(min = 1, message = "최소 주문 수량은 1개 이상이어야 합니다.")
  private int minimumOrder;
  @Range(min = 1, message = "최대 주문 수량은 1개 이상이어야 합니다.")
  private int maximumOrder;

  public String getOptionGroupName() {
    return optionGroupName;
  }

  public boolean isRequiredOption() {
    return requiredOption;
  }

  public boolean isExclusive() {
    return exclusive;
  }

  public int getMinimumOrder() {
    return minimumOrder;
  }

  public int getMaximumOrder() {
    return maximumOrder;
  }

  public OptionGroupRegister() {
  }

  @Builder
  public OptionGroupRegister(String optionGroupName, boolean requiredOption, boolean exclusive,
      int minimumOrder, int maximumOrder) {
    this.optionGroupName = optionGroupName;
    this.requiredOption = requiredOption;
    this.exclusive = exclusive;
    this.minimumOrder = minimumOrder;
    this.maximumOrder = maximumOrder;
  }

  public OptionGroup toOptionGroup(LoginUser user,
      Product product) {
    return new OptionGroup(this.optionGroupName, this.requiredOption, this.exclusive, this.minimumOrder, this.maximumOrder,
        product.getId(),user.getUserId());
  }
}
