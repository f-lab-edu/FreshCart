package com.example.freshcart.product.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.domain.exception.NotSellerException;
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
import com.example.freshcart.authentication.Role;
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
        request.isSingleType(),
        request.getCategoryId(),
        user.getUserId());

    productRepository.save(product);

    if (request.getOptionSet() != null) {
      for (OptionSet optionSet : request.getOptionSet()) {
        OptionGroupRegister optionGroupRegister = optionSet.getOptionGroupRegister();
        OptionGroup optionGroup = new OptionGroup(
            optionGroupRegister.getOptionGroupName(),
            optionGroupRegister.isRequiredOption(),
            optionGroupRegister.isExclusive(),
            optionGroupRegister.getMinimumOrder(),
            optionGroupRegister.getMaximumOrder(),
            product.getId(),
            user.getUserId());
        optionGroupRepository.save(optionGroup);

        List<OptionDetailRegister> optionDetailRegisterList = optionSet.getOptionDetailRegisterList();
        List<Option> options = optionSet.toOptions(optionDetailRegisterList, optionGroup);
        optionRepository.save(options);
      }
    }
  }

  //OptionId가 주어지면, Option을 찾는다.
  public Option getOption(Long optionId){
    return optionRepository.findById(optionId);
  }
}
