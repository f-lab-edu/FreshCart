package com.example.freshcart.stock.infrastructure;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.stock.application.StockReductionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

@Service
public class OptimisticStockReductionFacade {

  private StockReductionStrategy optimisticLockStockReduction;
//  @Autowired
  protected PlatformTransactionManager transactionManager;

  public OptimisticStockReductionFacade(
      StockReductionStrategy optimisticLockStockReduction,
      PlatformTransactionManager transactionManager) {
    this.optimisticLockStockReduction = optimisticLockStockReduction;
    this.transactionManager = transactionManager;
  }

  public void reduceProductInventory(OrderItem item, int count) throws InterruptedException {
    while(true) {
      try {
        TransactionStatus status =  transactionManager.getTransaction(null);
        optimisticLockStockReduction.reduceProductInventory(item, count);
        transactionManager.commit(status);
        break;
      } catch (Exception e) {
        Thread.sleep(50);
      }

      }
  }

}
