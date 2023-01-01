package com.example.freshcart.product.domain;

import java.util.List;

public interface OptionRepository {

//  Option save(Option option);
  List<Option> save(List<Option> options);
  Option findById(Long optionId);
}
