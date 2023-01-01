package com.example.freshcart.product.domain;

import java.util.List;

public interface ProductRepository {

  Product save(Product product);

  List<Product> findAll();

  Product findById(Long productId);

  Product saveWithOptions(Product product);
}
