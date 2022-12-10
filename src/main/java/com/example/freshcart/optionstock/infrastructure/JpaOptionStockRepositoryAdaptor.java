package com.example.freshcart.optionstock.infrastructure;

import com.example.freshcart.optionstock.domain.JpaOptionStockRepository;
import com.example.freshcart.optionstock.domain.OptionStock;
import com.example.freshcart.optionstock.domain.OptionStockRepository;
import com.example.freshcart.optionstock.domain.exception.OptionStockNotFoundException;


public class JpaOptionStockRepositoryAdaptor implements OptionStockRepository {
  private final JpaOptionStockRepository jpaOptionStockRepository;

  public JpaOptionStockRepositoryAdaptor(
      JpaOptionStockRepository jpaOptionStockRepository) {
    this.jpaOptionStockRepository = jpaOptionStockRepository;
  }

  @Override
  public void save(OptionStock optionStock) {
    jpaOptionStockRepository.save(optionStock);
  }

  @Override
  public OptionStock findById(Long optionStockId) {
    return jpaOptionStockRepository.findById(optionStockId).orElseThrow(
        OptionStockNotFoundException::new);
  }
}
