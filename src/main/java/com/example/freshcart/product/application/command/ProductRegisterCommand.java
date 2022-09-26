package com.example.freshcart.product.application.command;

import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.Product.Status;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * 셀러가 필수 정보를 담아서 회원 가입 요청. 필수이기 때문에 @NotNull과 @Valid로 확인
 */
public class ProductRegisterCommand {

  @NotNull
  private String name;
  @NotNull
  private int price;
  @NotNull
  private Status status;
  @NotNull
  private String description;
  @NotNull
  private Boolean singleType;
  @NotNull
  private int categoryId;

  private List<OptionGroup> optionGroupList;

  public ProductRegisterCommand(String name, int price,
      Status status, String description, Boolean singleType, int categoryId) {
    this.name = name;
    this.price = price;
    this.status = status;
    this.description = description;
    this.singleType = singleType;
    this.categoryId = categoryId;
  }

  public ProductRegisterCommand(String name, int price,
      Status status, String description, Boolean singleType, int categoryId,
      List<OptionGroup> optionGroupList) {
    this.name = name;
    this.price = price;
    this.status = status;
    this.description = description;
    this.singleType = singleType;
    this.categoryId = categoryId;
    this.optionGroupList = optionGroupList;
  }

  public ProductRegisterCommand() {
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

  public String getDescription() {
    return description;
  }

  public Boolean getSingleType() {
    return singleType;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public List<OptionGroup> getOptionGroupList() {
    return optionGroupList;
  }
}
