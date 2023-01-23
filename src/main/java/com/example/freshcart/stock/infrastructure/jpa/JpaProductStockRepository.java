package com.example.freshcart.stock.infrastructure.jpa;

import com.example.freshcart.stock.domain.ProductStock;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface JpaProductStockRepository extends JpaRepository<ProductStock, Long> {

  @Lock(value = LockModeType.PESSIMISTIC_WRITE)
  @Query("select p from ProductStock p where p.id = :productId")
  ProductStock findByProductIdWithPessimisticLock(Long productId);

  ProductStock findByProductId(Long productId);

  Optional<ProductStock> findById(Long productStockId);
}
