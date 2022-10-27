package com.example.freshcart.product.domain;

public interface OptionRepository {

  Option save(Option option);

  Option findById(Long productOptionId);
}
