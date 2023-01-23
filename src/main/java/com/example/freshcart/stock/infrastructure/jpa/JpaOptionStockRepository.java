package com.example.freshcart.stock.infrastructure.jpa;
import com.example.freshcart.stock.domain.OptionStock;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface JpaOptionStockRepository extends JpaRepository<OptionStock, Long> {

  OptionStock findByOptionId(Long optionId);
  Optional<OptionStock> findById(Long Id);

  @Lock(value = LockModeType.PESSIMISTIC_WRITE)
  @Query("select o from OptionStock o where o.id = :optionId")
  OptionStock findByOptionIdWithPessimisticLock(Long optionId);

}
