package com.example.freshcart.product.domain;

import com.example.freshcart.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
  User save(Product product);

  Optional<Product> findByProductId(Long id);

  List<Product> findAll();
}
