package com.example.freshcart.legacy.mybatis;

import com.example.freshcart.order.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {

  void insert(OrderItem orderItem);
}
