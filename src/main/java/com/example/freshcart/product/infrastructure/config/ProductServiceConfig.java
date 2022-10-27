package com.example.freshcart.product.infrastructure.config;

import com.example.freshcart.product.application.ProductService;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

  @Bean
  public ProductService productService(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository, OptionRepository optionRepository) {
    return new ProductService(productRepository, optionGroupRepository, optionRepository);
  }
}
