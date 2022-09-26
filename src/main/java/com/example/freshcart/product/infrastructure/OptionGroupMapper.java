package com.example.freshcart.product.infrastructure;

import com.example.freshcart.product.domain.OptionGroup;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionGroupMapper {
  void insert(OptionGroup optionGroup);
  List<OptionGroup> findByProductId(Long id);
}
