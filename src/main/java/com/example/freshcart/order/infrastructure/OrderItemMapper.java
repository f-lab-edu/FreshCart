package com.example.freshcart.order.infrastructure;

import com.example.freshcart.order.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {

  void insert(OrderItem orderItem);
}
