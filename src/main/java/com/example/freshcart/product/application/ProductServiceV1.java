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
public class ProductServiceV1 implements ProductService {

  private ProductRepository productRepository;
  private OptionGroupRepository optionGroupRepository;
  private OptionRepository optionRepository;

  public ProductServiceV1(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository,
      OptionRepository optionRepository) {
    this.productRepository = productRepository;
    this.optionGroupRepository = optionGroupRepository;
    this.optionRepository = optionRepository;
  }

  @Override
  @Transactional
  public void register(LoginUser user, ProductRegisterRequest request) {
    if (user.getRole() != Role.SELLER) {
      throw new NotSellerException();
    }
    addProduct(user, request);
  }


  /**
   * 단일 제품일 경우 product만 저장하고 옵션이 있는 제품은 OptionGroupRegister와 Option 저장.
   */

  @Override
  public void addProduct(LoginUser user, ProductRegisterRequest request) {
    Product product = request.toProduct(user);
    productRepository.save(product);

    if (request.getOptionSet() != null) {
      for (OptionSet optionSet : request.getOptionSet()) {
        OptionGroupRegister optionGroupRegister = optionSet.getOptionGroupRegister();
        OptionGroup optionGroup = optionGroupRegister.toOptionGroup(user, product);
        optionGroupRepository.save(optionGroup);

        List<OptionDetailRegister> optionDetailRegisterList = optionSet.getOptionDetailRegisterList();
        List<Option> options = optionSet.toOptions(optionDetailRegisterList, optionGroup, product.getId());
        optionRepository.save(options);
      }
    }
  }

}
