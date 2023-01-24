package com.example.freshcart.stock.infrastructure.jpa;

import com.example.freshcart.stock.infrastructure.jpa.JpaOptionStockRepository;
import com.example.freshcart.stock.domain.OptionStock;
import com.example.freshcart.stock.domain.OptionStockRepository;
import com.example.freshcart.stock.domain.exception.OptionStockNotFoundException;


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
  public OptionStock findById(Long Id) {
    return jpaOptionStockRepository.findById(Id).orElseThrow(OptionStockNotFoundException::new);

  }

  @Override
  public OptionStock findByOptionId(Long optionId) {
    return jpaOptionStockRepository.findByOptionId(optionId);
  }

  @Override
  public OptionStock findByOptionIdWithPessimisticLock(Long optionId) {
    return jpaOptionStockRepository.findByOptionIdWithPessimisticLock(optionId);
  }

  @Override
  public void deleteAll() {
    jpaOptionStockRepository.deleteAll();
  }
}
