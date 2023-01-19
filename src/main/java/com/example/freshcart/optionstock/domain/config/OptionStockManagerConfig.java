package com.example.freshcart.optionstock.domain.config;

import com.example.freshcart.optionstock.application.OptionStockManager;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.ProductStockRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;

public class OptionStockManagerConfig {

  @Bean
  public OptionStockManager optionStockManager(
      OptionStockRepository optionStockRepository,
      OptionRepository optionRepository,
      ProductStockRepository productStockRepository,
      ProductRepository productRepository){
  return new OptionStockManager(optionStockRepository,optionRepository, productStockRepository, productRepository);
  }
}
