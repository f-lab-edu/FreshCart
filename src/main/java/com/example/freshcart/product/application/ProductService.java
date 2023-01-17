package com.example.freshcart.product.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.domain.Option;
import com.example.freshcart.product.presentation.request.ProductRegisterRequest;

/**
 * V1: REPOSITORY를 각각 INSERT함 V2: 배치를 활용함 Service 로직이 다르기 때문에, 다른 구현체를 주입함.
 */
public interface ProductService {

  void register(LoginUser user, ProductRegisterRequest request);

  void addProduct(LoginUser user, ProductRegisterRequest request);

  Option getOption(Long optionId);
}
