package com.example.freshcart.product.domain;

import java.util.List;

public interface OptionGroupRepository {
  OptionGroup save(OptionGroup optionGroup);
  List<OptionGroup> findByProductId(Long id);
}
