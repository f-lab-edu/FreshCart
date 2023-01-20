package com.example.freshcart.optionstock.infrastructure.config.optionstockconfig;

import com.example.freshcart.optionstock.domain.jpa.JpaOptionStockRepository;
import com.example.freshcart.optionstock.domain.jpa.JpaProductStockRepository;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.ProductStockRepository;
import com.example.freshcart.optionstock.infrastructure.JpaOptionStockRepositoryAdaptor;
import com.example.freshcart.optionstock.infrastructure.JpaProductStockRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OptionStockRepositoryConfig {

  @Bean
  public OptionStockRepository optionStockRepository(JpaOptionStockRepository jpaOptionStockRepository){
    return new JpaOptionStockRepositoryAdaptor(jpaOptionStockRepository);
  }

  @Bean
  public ProductStockRepository productStockRepository(
      JpaProductStockRepository jpaProductStockRepository){
    return new JpaProductStockRepositoryAdaptor(jpaProductStockRepository);
  }
}
