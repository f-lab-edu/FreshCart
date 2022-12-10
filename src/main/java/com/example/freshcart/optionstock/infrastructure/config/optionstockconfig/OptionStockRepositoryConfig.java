package com.example.freshcart.optionstock.infrastructure.config.optionstockconfig;

import com.example.freshcart.optionstock.domain.JpaOptionStockRepository;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.infrastructure.JpaOptionStockRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OptionStockRepositoryConfig {

  @Bean
  public OptionStockRepository OptionStockRepository(JpaOptionStockRepository jpaOptionStockRepository){
    return new JpaOptionStockRepositoryAdaptor(jpaOptionStockRepository);
  }
}
