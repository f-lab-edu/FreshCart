package com.example.freshcart.product.infrastructure;
import com.example.freshcart.product.domain.Option;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionMapper {

  void insert(List<Option> option);
  Option findById(Long optionId);
}
