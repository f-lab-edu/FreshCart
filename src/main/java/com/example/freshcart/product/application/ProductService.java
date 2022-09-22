package com.example.freshcart.product.application;

import com.example.freshcart.product.domain.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void register(ProductRegisterCommand request){
    log.info("Seller일 경우 register method가 정상 호출됩니다");
  }
}
