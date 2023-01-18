package com.example.freshcart.optionstock.domain;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOptionStockRepository extends JpaRepository<OptionStock, Long> {

  OptionStock findByOptionId(Long optionId);
  Optional<OptionStock> findById(Long Id);

}
