package com.example.freshcart.optionstock.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductStockRepository extends JpaRepository<ProductStock, Long> {

  ProductStock findByProductId(Long productId);
  Optional<ProductStock> findById(Long productStockId);
}
