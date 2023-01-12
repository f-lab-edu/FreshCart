package com.example.freshcart.product.infrastructure.config;

import com.example.freshcart.product.application.ProductService;
import com.example.freshcart.product.application.ProductServiceV1;
import com.example.freshcart.product.application.ProductServiceV2;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.infrastructure.jdbc.JdbcProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

  @Bean
  public ProductService productService(ProductRepository productRepository,
      OptionGroupRepository optionGroupRepository, OptionRepository optionRepository) {
    return new ProductServiceV1(productRepository, optionGroupRepository, optionRepository);
  }

//  @Bean
//  public ProductService productService(JdbcProductRepository jdbcProductRepository, ProductRepository productRepository,
//      OptionGroupRepository optionGroupRepository, OptionRepository optionRepository) {
//    return new ProductServiceV2(jdbcProductRepository, productRepository, optionGroupRepository, optionRepository);
//  }
}
