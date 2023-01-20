package com.example.freshcart.optionstock.domain;

import com.example.freshcart.product.domain.Product;

public interface ProductStockRepository {
  void save(ProductStock productStock);
  ProductStock findByProductIdWithPessimisticLock(Long productId);

  ProductStock findByProductId(Long productId);
  ProductStock findById(Long productStockId);

}
