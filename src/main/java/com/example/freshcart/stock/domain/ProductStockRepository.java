package com.example.freshcart.stock.domain;

public interface ProductStockRepository {
  ProductStock findByProductIdWithPessimisticLock(Long productId);
  ProductStock findByProductIdWithOptimisticLock(Long productId);

  ProductStock findByProductId(Long productId);

  void save(ProductStock productStock);
  ProductStock findById(Long productStockId);
  void deleteAll();

  ProductStock saveAndFlush(ProductStock stock);
}
