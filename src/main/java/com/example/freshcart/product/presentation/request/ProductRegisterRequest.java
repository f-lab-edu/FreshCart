package com.example.freshcart.product.presentation.request;

import com.example.freshcart.product.domain.Product.Status;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 셀러가 필수 정보를 담아서 회원 가입 요청. 필수이기 때문에 @NotNull과 @Valid로 확인
 */
public class ProductRegisterRequest {

  @NotBlank(message = "제품 이름을 입력해주세요")
  private String name;
  @Positive(message = "제품가격은 0 이상이어야 합니다")
  private int price;
  @NotNull
  private Status status;
  @NotBlank(message = "상품 설명을 입력해주세요")
  private String description;
  @NotNull(message = "단일 상품 여부를 입력해주세요")
  private boolean singleType;
  @Positive()
  private int categoryId;

  private List<OptionSet> optionSet;


  public ProductRegisterRequest() {
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

  public List<OptionSet> getOptionSet() {
    return optionSet;
  }
}
