package com.example.freshcart.stock.infrastructure.config;

import com.example.freshcart.stock.application.StockReductionStrategy;
import com.example.freshcart.stock.infrastructure.stockreduction.GeneralStockReduction;
import com.example.freshcart.stock.infrastructure.jpa.JpaOptionStockRepository;
import com.example.freshcart.stock.infrastructure.jpa.JpaProductStockRepository;
import com.example.freshcart.stock.domain.OptionStockRepository;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.stock.infrastructure.jpa.JpaOptionStockRepositoryAdaptor;
import com.example.freshcart.stock.infrastructure.jpa.JpaProductStockRepositoryAdaptor;
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
//    return new PessimisticLockStock(optionStockRepository, productStockRepository);
//    return new OptimisticLockStock(optionStockRepository, productStockRepository);
  }
}
