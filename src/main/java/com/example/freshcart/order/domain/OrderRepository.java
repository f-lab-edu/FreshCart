package com.example.freshcart.order.domain;

import com.example.freshcart.authentication.application.LoginUser;

public interface OrderRepository {

  Order save(LoginUser user, Order order);
}
