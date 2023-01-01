package com.example.freshcart.product.presentation.request;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.Product.Status;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

/**
 * 셀러가 필수 정보를 담아서 회원 가입 요청. 필수이기 때문에 @NotNull과 @Valid로 확인 Command 로 변환하여 전달 필요. Product-Status를 참조하고
 * 있음.
 */

@Getter
@NoArgsConstructor
public class ProductRegisterRequest {

  @NotBlank(message = "제품 이름을 입력해주세요")
  private String name;
  @Range(min = 10, message = "제품가격은 최소 10원 이상이어야 합니다")
  private int price;
  @NotNull
  private Status status;
  @NotBlank(message = "상품 설명을 입력해주세요")
  private String description;
  @NotNull(message = "단일 상품 여부를 입력해주세요")
  private boolean singleType;
  @Range(min = 1, message = "ID는 최소 1이상 입니다.")
  private int categoryId;
  private List<OptionSet> optionSet;


  @Builder
  public ProductRegisterRequest(String name, int price, Status status, String description,
      boolean singleType, int categoryId, List<OptionSet> optionSet) {
    this.name = name;
    this.price = price;
    this.status = status;
    this.description = description;
    this.singleType = singleType;
    this.categoryId = categoryId;
    this.optionSet = optionSet;
  }

  public Product toProduct(LoginUser user) {
    return new Product(this.name, this.price, this.status, this.description, this.singleType,
        this.categoryId,
        user.getUserId());
  }
}
