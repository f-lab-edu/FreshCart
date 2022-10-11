package com.example.freshcart.product.infrastructure.config;

import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.infrastructure.OptionGroupMapper;
import com.example.freshcart.product.infrastructure.OptionGroupMapperRepositoryAdaptor;
import com.example.freshcart.product.infrastructure.OptionMapper;
import com.example.freshcart.product.infrastructure.OptionMapperRepositoryAdaptor;
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

  @Bean
  public OptionGroupRepository optionGroupRepository(OptionGroupMapper optionGroupMapper) {
    return new OptionGroupMapperRepositoryAdaptor(optionGroupMapper);
  }

  @Bean
  public OptionRepository optionRepository(OptionMapper optionMapper) {
    return new OptionMapperRepositoryAdaptor(optionMapper);
  }
}
