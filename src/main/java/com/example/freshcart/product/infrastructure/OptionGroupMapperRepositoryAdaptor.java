package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.OptionGroup;
import com.example.freshcart.product.domain.OptionGroupRepository;
import java.util.List;

public class OptionGroupMapperRepositoryAdaptor implements OptionGroupRepository {

  private final OptionGroupMapper optionGroupMapper;

  public OptionGroupMapperRepositoryAdaptor(
      OptionGroupMapper optionGroupMapper) {
    this.optionGroupMapper = optionGroupMapper;
  }

  @Override
  public OptionGroup save(OptionGroup optionGroup) {
    optionGroupMapper.insert(optionGroup);
    return optionGroup;
  }

  @Override
  public List<OptionGroup> findByProductId(Long id) {
    return optionGroupMapper.findByProductId(id);
  }
}