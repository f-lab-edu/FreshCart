package com.example.freshcart.stock.infrastructure.config.optionstockconfig;

import com.example.freshcart.stock.application.StockReductionStrategy;
import com.example.freshcart.stock.infrastructure.GeneralStockReduction;
import com.example.freshcart.stock.infrastructure.jpa.JpaOptionStockRepository;
import com.example.freshcart.stock.infrastructure.jpa.JpaProductStockRepository;
import com.example.freshcart.stock.domain.OptionStockRepository;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.stock.infrastructure.JpaOptionStockRepositoryAdaptor;
import com.example.freshcart.stock.infrastructure.JpaProductStockRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockRepositoryConfig {

  @Bean
  public OptionStockRepository optionStockRepository(JpaOptionStockRepository jpaOptionStockRepository){
    return new JpaOptionStockRepositoryAdaptor(jpaOptionStockRepository);
  }

  @Bean
  public ProductStockRepository productStockRepository(
      JpaProductStockRepository jpaProductStockRepository){
    return new JpaProductStockRepositoryAdaptor(jpaProductStockRepository);
  }

  @Bean
  public StockReductionStrategy stockReductionStrategy(OptionStockRepository optionStockRepository, ProductStockRepository productStockRepository){
    return new GeneralStockReduction(optionStockRepository, productStockRepository);
  }
}
