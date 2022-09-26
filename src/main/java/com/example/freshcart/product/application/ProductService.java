package com.example.freshcart.product.application;

import com.example.freshcart.product.application.command.ProductRegisterCommand;
import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.infrastructure.exception.NotSellerException;
import com.example.freshcart.user.application.LoginUser;
import com.example.freshcart.user.domain.Role;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService {

  private ProductRepository productRepository;
  private OptionGroupRepository optionGroupRepository;

  public ProductService(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository) {
    this.productRepository = productRepository;
    this.optionGroupRepository = optionGroupRepository;
  }

  public void register(LoginUser user, ProductRegisterCommand request) {
    //Seller 가 아닐 경우 재확인 - 예외처리
    if (user.getRole() != Role.SELLER) {
      throw new NotSellerException();
    }
    //단일 상품 / 옵션이 있는 상품 등록 분기 - 순서 바꿔주기.
    if(!isSingleType(request)) {
      productWithOptionRegister(user, request);
    } else {
      singleRegister(user, request);
    }
  }

  public Boolean isSingleType(ProductRegisterCommand request){
    boolean answer = false;
    if(request.getSingleType()==true){
      answer = true;
    }
    return answer;
  }

  public Product singleRegister(LoginUser user, ProductRegisterCommand request) {
    Product product = new Product(
        request.getName(),
        request.getPrice(),
        request.getStatus(),
        request.getDescription(),
        request.getSingleType(),
        request.getCategoryId(),
        user.getId());
    return productRepository.save(product);
  }

  public Product productWithOptionRegister(LoginUser user, ProductRegisterCommand request) {
    List<OptionGroup> list = request.getOptionGroupList();
    for(OptionGroup element: list){
    optionGroupRepository.save(element);
    }
      Product product = new Product(
          request.getName(),
          request.getPrice(),
          request.getStatus(),
          request.getDescription(),
          request.getSingleType(),
          request.getCategoryId(),
          user.getId());
      return productRepository.save(product);
  }
}
