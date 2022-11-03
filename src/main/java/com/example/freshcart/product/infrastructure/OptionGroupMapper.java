package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.OptionGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionGroupMapper {

  void insert(OptionGroup optionGroup);

  OptionGroup findById(Long id);
}
