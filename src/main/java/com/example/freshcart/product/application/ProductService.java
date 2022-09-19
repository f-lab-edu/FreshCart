package com.example.freshcart.product.application;

import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.user.domain.UserRepository;

public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void register(ProductRegisterCommand request){

  }


}
