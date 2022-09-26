package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.Option;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionMapper {
  void insert(Option option);
}
