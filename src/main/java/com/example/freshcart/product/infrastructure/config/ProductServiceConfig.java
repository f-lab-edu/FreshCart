package com.example.freshcart.product.infrastructure.config;

import com.example.freshcart.product.application.ProductService;
import com.example.freshcart.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductService(productRepository);
  }
}
