package com.example.freshcart.order.infrastructure.mybatis;

import com.example.freshcart.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

  void insert(Order order);
}
