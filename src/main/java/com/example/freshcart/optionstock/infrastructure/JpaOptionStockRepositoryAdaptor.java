package com.example.freshcart.optionstock.infrastructure;

import com.example.freshcart.optionstock.domain.jpa.JpaOptionStockRepository;
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
  public OptionStock findByOptionId(Long optionId) {
    return jpaOptionStockRepository.findByOptionId(optionId);
  }

  @Override
  public OptionStock findById(Long Id) {
    return jpaOptionStockRepository.findById(Id).orElseThrow(OptionStockNotFoundException::new);

  }

  @Override
  public OptionStock findByProductIdWithPessimisticLock(Long optionId) {
    return jpaOptionStockRepository.findByProductIdWithPessimisticLock(optionId);
  }
}
