package com.example.freshcart.order.infrastructure;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemOptionGroupMapper {

  void insert(OrderItemOptionGroup orderItemOptionGroup);
}
