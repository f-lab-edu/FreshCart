package com.example.freshcart.order.infrastructure.mybatis;

import com.example.freshcart.order.domain.OrderItemOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemOptionMapper {

  void insert(OrderItemOption orderItemOption);
}
