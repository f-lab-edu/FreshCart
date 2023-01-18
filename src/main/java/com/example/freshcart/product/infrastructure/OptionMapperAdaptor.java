package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.domain.OptionRepository;
import java.util.List;


public class OptionMapperAdaptor implements OptionRepository {

  private final OptionMapper optionMapper;

  public OptionMapperAdaptor(
      OptionMapper optionMapper) {
    this.optionMapper = optionMapper;
  }

  @Override
  public List<Option> save(List<Option> options) {
    optionMapper.insert(options);
    return options;
  }

  @Override
  public Option findById(Long optionId) {
    return optionMapper.findById(optionId);
  }
}
