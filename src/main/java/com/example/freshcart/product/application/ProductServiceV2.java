package com.example.freshcart.product.application;

import com.example.freshcart.authentication.Role;
import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.domain.exception.NotSellerException;
import com.example.freshcart.product.infrastructure.jdbc.JdbcProductRepository;
import com.example.freshcart.product.presentation.request.OptionDetailRegister;
import com.example.freshcart.product.presentation.request.OptionGroupRegister;
import com.example.freshcart.product.presentation.request.OptionSet;
import com.example.freshcart.product.presentation.request.ProductRegisterRequest;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public class ProductServiceV2 implements ProductService {

  private final JdbcProductRepository jdbcProductRepository;
  private final ProductRepository productRepository;
  private final OptionGroupRepository optionGroupRepository;
  private final OptionRepository optionRepository;

  public ProductServiceV2(
      JdbcProductRepository jdbcProductRepository,
      ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository,
      OptionRepository optionRepository) {
    this.jdbcProductRepository = jdbcProductRepository;
    this.productRepository = productRepository;
    this.optionGroupRepository = optionGroupRepository;
    this.optionRepository = optionRepository;
  }

  @Override
  @Transactional
  public void register(LoginUser user, ProductRegisterRequest request) {
    //Seller 가 아닐 경우 재확인 - 예외처리
    if (user.getRole() != Role.SELLER) {
      throw new NotSellerException();
    }
    addProduct(user, request);
  }

  @Override
  public void addProduct(LoginUser user, ProductRegisterRequest request) {
    Product product = request.toProduct(user);
    if(request.getOptionSet() == null){
      productRepository.save(product);
    }

    //request객체를 Product로 변환.
    if(request.getOptionSet() != null){
      for (OptionSet optionSet : request.getOptionSet()) {
        OptionGroupRegister optionGroupRegister = optionSet.getOptionGroupRegister();
        OptionGroup optionGroup = optionGroupRegister.toOptionGroup(user, product);
//        optionGroupRepository.save(optionGroup);
        List<OptionDetailRegister> optionDetailRegisterList = optionSet.getOptionDetailRegisterList();
        List<Option> options = optionSet.toOptions(optionDetailRegisterList, optionGroup);
//        optionRepository.save(options);
      }

      jdbcProductRepository.batchInsertOptionalProducts(user.getUserId(), product);
    }
    //OptionalProduct이면 saveAll()을
  }

  @Override
  public Option getOption(Long optionId) {
    return null;
  }
}
