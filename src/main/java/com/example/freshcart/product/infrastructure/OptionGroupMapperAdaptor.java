package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.OptionGroupRepository;

public class OptionGroupMapperAdaptor implements OptionGroupRepository {

  private final OptionGroupMapper optionGroupMapper;

  public OptionGroupMapperAdaptor(
      OptionGroupMapper optionGroupMapper) {
    this.optionGroupMapper = optionGroupMapper;
  }

  @Override
  public OptionGroup save(OptionGroup optionGroup) {
    optionGroupMapper.insert(optionGroup);
    return optionGroup;
  }

  @Override
  public OptionGroup findById(Long optionGroupId) {
    return optionGroupMapper.findById(optionGroupId);
  }
}
