package com.example.freshcart.optionstock.domain.config;

import com.example.freshcart.optionstock.application.OptionStockManager;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.product.domain.OptionRepository;
import org.springframework.context.annotation.Bean;

public class OptionStockManagerConfig {

  @Bean
  public OptionStockManager optionStockManager(OptionStockRepository optionStockRepository, OptionRepository optionRepository){
  return new OptionStockManager(optionStockRepository,optionRepository);
  }
}
