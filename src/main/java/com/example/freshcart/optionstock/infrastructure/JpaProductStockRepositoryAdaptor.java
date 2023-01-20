package com.example.freshcart.optionstock.infrastructure;

import com.example.freshcart.optionstock.domain.jpa.JpaProductStockRepository;
import com.example.freshcart.optionstock.domain.ProductStock;
import com.example.freshcart.optionstock.domain.ProductStockRepository;
import com.example.freshcart.optionstock.domain.exception.ProductStockNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaProductStockRepositoryAdaptor implements ProductStockRepository {
  private final JpaProductStockRepository jpaProductStockRepository;

  @Override
  public void save(ProductStock productStock) {
    jpaProductStockRepository.save(productStock);
  }

  @Override
  public ProductStock findByProductIdWithPessimisticLock(Long productId) {
    return jpaProductStockRepository.findByProductIdWithPessimisticLock(productId);
  }

  @Override
  public ProductStock findByProductId(Long productId) {
    return jpaProductStockRepository.findByProductId(productId);
  }

  @Override
  public ProductStock findById(Long productStockId) {
    return jpaProductStockRepository.findById(productStockId).orElseThrow(
        ProductStockNotFoundException::new);
  }
}
