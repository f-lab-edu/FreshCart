package com.example.freshcart.product.application;

import com.example.freshcart.product.application.command.ProductRegisterCommand;
import com.example.freshcart.product.domain.Product;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.infrastructure.exception.NotSellerException;
import com.example.freshcart.user.application.LoginUser;
import com.example.freshcart.user.domain.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product register(LoginUser user, ProductRegisterCommand request) {
    log.info("인터셉터를 사용할 경우 서비스 로직 호출되지 않습니다");
    if (user.getRole() != Role.SELLER) {
      throw new NotSellerException();
    }

    log.info("Seller일 경우 register method가 정상 호출됩니다");
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
