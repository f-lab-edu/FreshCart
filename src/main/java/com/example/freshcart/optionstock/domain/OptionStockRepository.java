package com.example.freshcart.optionstock.domain;

public interface OptionStockRepository {

  void save(OptionStock optionStock);

  OptionStock findById(Long productOptionId);
}
