package com.example.freshcart.optionstock.domain;

public interface OptionStockRepository {

  void save(OptionStock optionStock);

  OptionStock findByOptionId(Long optionId);

  OptionStock findById(Long Id);

}
