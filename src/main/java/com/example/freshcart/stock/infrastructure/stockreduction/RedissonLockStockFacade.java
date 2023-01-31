package com.example.freshcart.stock.infrastructure.stockreduction;

import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.stock.application.StockReductionStrategy;
import java.util.concurrent.TimeUnit;
import lombok.Synchronized;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;


@Component
public class RedissonLockStockFacade {

  private RedissonClient redissonClient;
  private StockReductionStrategy generalStockReduction;
  private PlatformTransactionManager transactionManager;

  public RedissonLockStockFacade(RedissonClient redissonClient,
      StockReductionStrategy generalStockReduction,
      PlatformTransactionManager transactionManager) {
    this.redissonClient = redissonClient;
    this.generalStockReduction = generalStockReduction;
    this.transactionManager = transactionManager;
  }

  public void reduceProductInventory(OrderItem item, int count) throws InterruptedException {
    RLock lock = redissonClient.getLock(item.getProductId().toString());
    try {
      boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
      if (!available) {
        System.out.println("lock 획득에 실패하였습니다");
        return;
      }
      TransactionStatus status = transactionManager.getTransaction(null);
      generalStockReduction.reduceProductInventory(item, count);
      transactionManager.commit(status);
    } finally {
      lock.unlock();
    }
  }
}
