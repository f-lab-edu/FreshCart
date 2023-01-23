package com.example.freshcart.stock.infrastructure;

import com.example.freshcart.stock.domain.ProductStock;
import com.example.freshcart.stock.domain.ProductStockRepository;
import com.example.freshcart.stock.domain.exception.ProductStockNotFoundException;
import com.example.freshcart.stock.infrastructure.jpa.JpaProductStockRepository;
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

  @Override
  public void deleteAll() {

  }

  @Override
  public ProductStock saveAndFlush(ProductStock stock) {
    return jpaProductStockRepository.saveAndFlush(stock);
  }
}
