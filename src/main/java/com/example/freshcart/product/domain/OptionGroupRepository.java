package com.example.freshcart.product.domain;


public interface OptionGroupRepository {

  OptionGroup save(OptionGroup optionGroup);

  OptionGroup findById(Long productOptionGroupId);
}
