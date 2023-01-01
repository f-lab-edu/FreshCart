package com.example.freshcart.product.presentation.request;

import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionGroup;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * OptionGroup 한 개당 하나 이상의 옵션을 갖는다 (ex. 중량 - 50g, 100g 등)
 */

@NoArgsConstructor
public class OptionSet {

  @NotBlank(message = "옵션 그룹을 최소 하나 입력하세요")
  private OptionGroupRegister optionGroupRegister;
  @NotBlank(message = "그룹 하나당 최소 하나의 옵션을 입력하세요")
  private List<OptionDetailRegister> optionDetailRegisterList;

  @Builder
  public OptionSet(
      OptionGroupRegister optionGroupRegister,
      List<OptionDetailRegister> optionDetailRegisterList) {
    this.optionGroupRegister = optionGroupRegister;
    this.optionDetailRegisterList = optionDetailRegisterList;
  }

  public OptionGroupRegister getOptionGroupRegister() {
    return optionGroupRegister;
  }

  public List<OptionDetailRegister> getOptionDetailRegisterList() {
    return optionDetailRegisterList;
  }

  public List<Option> toOptions(List<OptionDetailRegister> optionDetailRegisterList, OptionGroup optionGroup){
    return optionDetailRegisterList
        .stream()
        .map(optionDetailRegister ->
            Option.builder()
                .name(optionDetailRegister.getName())
                .price(optionDetailRegister.getPrice())
                .optionGroupId(optionGroup.getId())
                .sellerId(optionGroup.getSellerId())
                .build())
        .collect(Collectors.toList());
  }

}
