package com.example.freshcart.product.domain;

import com.example.freshcart.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  Product save(Product product);

  List<Product> findAll();
}
