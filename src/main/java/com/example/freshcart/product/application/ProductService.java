package com.example.freshcart.product.application;

import com.example.freshcart.product.presentation.request.OptionDetailRegister;
import com.example.freshcart.product.presentation.request.OptionGroupRegister;
import com.example.freshcart.product.presentation.request.OptionSet;
import com.example.freshcart.product.presentation.request.ProductRegisterRequest;
import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.infrastructure.exception.NotSellerException;
import com.example.freshcart.user.application.LoginUser;
import com.example.freshcart.global.domain.Role;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ProductService {

  private ProductRepository productRepository;
  private OptionGroupRepository optionGroupRepository;
  private OptionRepository optionRepository;

  public ProductService(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository,
      OptionRepository optionRepository) {
    this.productRepository = productRepository;
    this.optionGroupRepository = optionGroupRepository;
    this.optionRepository = optionRepository;
  }


  @Transactional
  public void register(LoginUser user, ProductRegisterRequest request) {
    //Seller 가 아닐 경우 재확인 - 예외처리
    if (user.getRole() != Role.SELLER) {
      throw new NotSellerException();
    }
    addProduct(user, request);

  }


  /**
   * 단일 제품일 경우 product만 저장하고 옵션이 있는 제품은 OptionGroupRegister와 Option 저장.
   */
  public void addProduct(LoginUser user, ProductRegisterRequest request) {

    Product product = new Product(
        request.getName(),
        request.getPrice(),
        request.getStatus(),
        request.getDescription(),
        request.getSingleType(),
        request.getCategoryId(),
        user.getUserId());

    productRepository.save(product);
    log.info(" product id 확인: " + product.getId());

    if (request.getOptionSet() != null) {
      for (OptionSet optionSet : request.getOptionSet()) {
        OptionGroupRegister optionGroupRegister = optionSet.getOptionGroupRegister();
        OptionGroup optionGroup = new OptionGroup(
            optionGroupRegister.getOptionGroupName(),
            optionGroupRegister.isRequiredOption(),
            optionGroupRegister.isExclusive(),
            optionGroupRegister.getMinimumOrder(),
            optionGroupRegister.getMaximumOrder(),
            product.getId());
        optionGroupRepository.save(optionGroup);

        List<OptionDetailRegister> optionDetailRegisterList = optionSet.getOptionDetailRegisterList();
        for (OptionDetailRegister element : optionDetailRegisterList) {
          Option option = new Option(
              element.getOptionName(),
              element.getPrice(),
              optionGroup.getId());
          optionRepository.save(option);
        }
      }
    }
  }
}
