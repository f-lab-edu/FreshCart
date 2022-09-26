package com.example.freshcart.product.infrastructure.config;

import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.infrastructure.ProductMapper;
import com.example.freshcart.product.infrastructure.ProductMapperRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRepositoryConfig {

  @Bean
  public ProductRepository productRepository(ProductMapper productMapper) {
    return new ProductMapperRepositoryAdaptor(productMapper);
  }

}
