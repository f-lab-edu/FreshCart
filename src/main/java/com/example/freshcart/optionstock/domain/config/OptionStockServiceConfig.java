package com.example.freshcart.optionstock.domain.config;

import com.example.freshcart.optionstock.application.OptionStockService;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.product.application.ProductService;
import org.springframework.context.annotation.Bean;

public class OptionStockServiceConfig {

  @Bean
  public OptionStockService optionStockService(OptionStockRepository optionStockRepository, ProductService productService){
  return new OptionStockService(optionStockRepository,productService);
  }


}
