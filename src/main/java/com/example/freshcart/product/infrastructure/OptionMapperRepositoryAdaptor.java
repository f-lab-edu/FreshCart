package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionRepository;


public class OptionMapperRepositoryAdaptor implements OptionRepository {

  private final OptionMapper optionMapper;

  public OptionMapperRepositoryAdaptor(
      OptionMapper optionMapper) {
    this.optionMapper = optionMapper;
  }

  @Override
  public Option save(Option option) {
    optionMapper.insert(option);
    return option;
  }

  @Override
  public Option findById(Long optionId) {
    return optionMapper.findById(optionId);
  }
}
