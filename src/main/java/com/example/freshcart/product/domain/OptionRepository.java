package com.example.freshcart.product.domain;


import java.util.List;

public interface OptionRepository {

  List<Option> save(List<Option> options);

  Option findById(Long optionId);
}
