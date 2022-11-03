package com.example.freshcart.order.domain;

import com.example.freshcart.user.application.LoginUser;

public interface OrderRepository {

  Order save(LoginUser user, Order order);
}
