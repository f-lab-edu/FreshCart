package com.example.freshcart.stock.domain.config;

import com.example.freshcart.stock.application.StockManager;
import com.example.freshcart.stock.application.StockReduceProcessor;
import com.example.freshcart.stock.application.StockReductionStrategy;
import com.example.freshcart.stock.domain.OptionStockRepository;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockServiceConfig {

  @Bean
  public StockReduceProcessor stockReduceProcessor(StockReductionStrategy stockReductionStrategy){
    return new StockReduceProcessor(stockReductionStrategy);
  }
  @Bean
  public StockManager stockManager(
      OptionStockRepository optionStockRepository,
      OptionRepository optionRepository,
      ProductStockRepository productStockRepository,
      ProductRepository productRepository) {
    return new StockManager(optionStockRepository, optionRepository, productStockRepository,
        productRepository);
  }

}
