package com.example.freshcart.product.domain;

import java.util.List;

public interface OptionRepository {
  Option save(Option option);
  List<Option> findByOptionGroupId(Long id);

}
