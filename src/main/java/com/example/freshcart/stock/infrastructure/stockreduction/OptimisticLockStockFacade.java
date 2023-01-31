package com.example.freshcart.stock.infrastructure.stockreduction;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.stock.application.StockReductionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

@Component
public class OptimisticLockStockFacade {

  private StockReductionStrategy optimisticLockStockReduction;
  private PlatformTransactionManager transactionManager;

  public OptimisticLockStockFacade(
      StockReductionStrategy optimisticLockStockReduction,
      PlatformTransactionManager transactionManager) {
    this.optimisticLockStockReduction = optimisticLockStockReduction;
    this.transactionManager = transactionManager;
  }

  public void reduceProductInventory(OrderItem item, int count) throws InterruptedException {
    while (true) {
      try {
        TransactionStatus status = transactionManager.getTransaction(null);
        optimisticLockStockReduction.reduceProductInventory(item, count);
        transactionManager.commit(status);
        break;
      } catch (Exception e) {
        Thread.sleep(50);
      }
    }
  }
}
