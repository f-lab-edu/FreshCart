package com.example.freshcart.stock.domain;

public interface OptionStockRepository {

  void save(OptionStock optionStock);

  OptionStock findByOptionId(Long optionId);

  OptionStock findById(Long Id);

  OptionStock findByOptionIdWithPessimisticLock(Long optionId);

  void deleteAll();
}
