package com.example.freshcart.product.application.command;

import com.example.freshcart.product.domain.OptionGroup;
import java.util.List;

//OptionRegister로 놓는 게 맞을 거 같다. OptionGroup은 product 등록 시 받으니까.
public class OptionGroupRegisterCommand {
  List<OptionGroup> optionGroupList;

  public OptionGroupRegisterCommand(
      List<OptionGroup> optionGroupList) {
    this.optionGroupList = optionGroupList;
  }

  public List<OptionGroup> getOptionGroupList() {
    return optionGroupList;
  }
}
